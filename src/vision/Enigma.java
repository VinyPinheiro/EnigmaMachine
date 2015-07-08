package vision;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enigma.EnigmaMachine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.PageAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Enigma extends JFrame implements ItemListener, ActionListener, KeyListener {

	private JPanel p1, p2, p3, p4, p5, p6;
	private JComboBox<String>[] combo;
	private JLabel lbl[];
	private Boolean changeOK;
	private JButton btClear;
	private JTextField txtInput;
	private JTextField txtOutput;
	private JTextField txtOrigin;
	private EnigmaMachine enigma;

	public Enigma() {
		changeOK = true;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 300);
		getContentPane().setLayout(new GridLayout(2, 1));

		p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 18, 2, 2));
		getContentPane().add(p1);

		combo = new JComboBox[26];
		lbl = new JLabel[26];

		for (int i = 0; i < combo.length; i++) {
			combo[i] = new JComboBox<String>();
			combo[i].setModel(new DefaultComboBoxModel<String>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H",
					"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
			combo[i].setSelectedIndex(i);
			combo[i].setName("" + i);
			// combo[i].addMouseListener(this);
			combo[i].addItemListener(this);

			lbl[i] = new JLabel("   " + combo[i].getSelectedItem().toString() + " =");

			p1.add(lbl[i]);
			p1.add(combo[i]);
		}

		p3 = new JPanel();
		p3.setLayout(new BorderLayout());

		btClear = new JButton("Limpar");
		btClear.addActionListener(this);
		p3.add(btClear, "North");

		p5 = new JPanel();
		p5.setLayout(new FlowLayout(1));

		txtOrigin = new JTextField(20);
		txtOrigin.setEnabled(false);
		p5.add(txtOrigin);

		txtOutput = new JTextField(20);
		txtOutput.setEnabled(false);
		p5.add(txtOutput);

		p4 = new JPanel();
		p4.setLayout(new GridLayout(2, 1));

		txtInput = new JTextField(3);
		txtInput.addKeyListener(this);

		p6 = new JPanel();
		p6.setLayout(new FlowLayout(1));
		p6.add(txtInput);

		p4.add(p6);
		p4.add(p5);

		p2 = new JPanel();
		p2.setLayout(new GridLayout(2, 1));
		p2.add(p3);
		p2.add(p4);
		getContentPane().add(p2);
		
		enigma = new EnigmaMachine(generatePlugBoard());
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		changeOK = !changeOK;
		int index, otherIndex;
		JComboBox<String> choice;

		if (changeOK) {
			choice = ((JComboBox<String>) e.getItemSelectable());

			index = Integer.parseInt(choice.getName());
			otherIndex = choice.getSelectedIndex();
			if (combo[otherIndex].isEnabled()) {
				combo[otherIndex].setSelectedIndex(index);
				combo[otherIndex].setEnabled(false);
				combo[index].setEnabled(false);
				enigma.setPlugBoard(generatePlugBoard());
			} else {
				JOptionPane.showMessageDialog(this, "Plug ja ligado", "ERRO", JOptionPane.ERROR_MESSAGE);
				combo[index].setSelectedIndex(index);
				combo[index].setEnabled(true);

			}

			changeOK = !changeOK;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btClear) {
			for (JComboBox<String> jComboBox : combo) {
				jComboBox.removeItemListener(this);
				jComboBox.setSelectedIndex(Integer.parseInt(jComboBox.getName()));
				jComboBox.setEnabled(true);
				jComboBox.addItemListener(this);
			}

			txtOrigin.setText("");
			txtOutput.setText("");

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		char aux = (e.getKeyChar() + "").toUpperCase().charAt(0);
		
		if (!(aux > 64 && aux < 91)) {
			e.consume();
		} else {
			txtOrigin.setText(txtOrigin.getText() + aux);
			txtOutput.setText(txtOutput.getText() + enigma.Encode(aux));
		}
		txtInput.setText("");
		txtInput.setFocusable(true);
	}
	
	private char[] generatePlugBoard()
	{
		char[] plug = new char[26];
		for (int i = 0; i < combo.length; i++) {
			plug[i] = combo[i].getSelectedItem().toString().charAt(0);
		}
		return plug;
	}
}
