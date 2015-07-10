package vision;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enigma.EnigmaMachine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Enigma extends JFrame implements ItemListener, ActionListener, KeyListener {

	private JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9,p10;
	private JComboBox<String>[] cbPlugs;
	private JLabel lblPlugs[];
	private JComboBox<String>[] cbRotorAnel;
	private JLabel lblRotorAnel[];
	private JLabel lblRotorAnelLegend;
	private JComboBox<String>[] cbRotorOrder;
	private JLabel lblRotorOrderLegend;
	private JComboBox<String>[] cbRotorStart;
	private JLabel lblRotorStart[];
	private JLabel lblRotorStartLegend;
	private Boolean changeOK;
	private JButton btClear;
	private JTextField txtInput;
	private JTextField txtOutput;
	private JTextField txtOrigin;
	private EnigmaMachine enigma;

	public Enigma() {
		changeOK = true;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 400);
		getContentPane().setLayout(new GridLayout(3, 1));

		p10 = new JPanel();
		p10.setLayout(new FlowLayout(1));

		cbRotorOrder = new JComboBox[3];
		lblRotorOrderLegend = new JLabel("Posição dos rotores");
		p10.add(lblRotorOrderLegend);
		
		for (int i = 0; i < cbRotorOrder.length; i++) {
			
			cbRotorOrder[i] = new JComboBox<String>();
			cbRotorOrder[i].setModel(new DefaultComboBoxModel<String>(new String[] { "I", "II", "III"}));
			cbRotorOrder[i].setSelectedIndex(i);
			
			p10.add(cbRotorOrder[i]);
		}
		
		p7 = new JPanel();
		p7.setLayout(new FlowLayout(1));

		cbRotorAnel = new JComboBox[3];
		lblRotorAnel = new JLabel[3];
		lblRotorAnelLegend = new JLabel("Posição Inicial dos Aneis");
		p7.add(lblRotorAnelLegend);
		
		for (int i = 0; i < cbRotorAnel.length; i++) {
			lblRotorAnel[i] = new JLabel("   " + (i + 1) + " =");
			
			cbRotorAnel[i] = new JComboBox<String>();
			cbRotorAnel[i].setModel(new DefaultComboBoxModel<String>(new String[] { "A", "B", "C", "D", "E", "F", "G",
					"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
			cbRotorAnel[i].addItemListener(this);
			
			p7.add(lblRotorAnel[i]);
			p7.add(cbRotorAnel[i]);
		}
		
		p8 = new JPanel();
		p8.setLayout(new FlowLayout(1));

		cbRotorStart = new JComboBox[3];
		lblRotorStart = new JLabel[3];
		lblRotorStartLegend = new JLabel("Posição Inicial dos Rotores");
		p8.add(lblRotorStartLegend);
		for (int i = 0; i < cbRotorStart.length; i++) {
			lblRotorStart[i] = new JLabel("   " + (i + 1) + " =");
			
			cbRotorStart[i] = new JComboBox<String>();
			cbRotorStart[i].setModel(new DefaultComboBoxModel<String>(new String[] { "A", "B", "C", "D", "E", "F", "G",
					"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
			cbRotorStart[i].addItemListener(this);
			
			p8.add(lblRotorStart[i]);
			p8.add(cbRotorStart[i]);
		}

		p9 = new JPanel();
		p9.setLayout(new GridLayout(3, 1));
		p9.add(p10);
		p9.add(p7);
		p9.add(p8);

		p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 18, 2, 2));

		cbPlugs = new JComboBox[26];
		lblPlugs = new JLabel[26];

		for (int i = 0; i < cbPlugs.length; i++) {
			cbPlugs[i] = new JComboBox<String>();
			cbPlugs[i].setModel(new DefaultComboBoxModel<String>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H",
					"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
			cbPlugs[i].setSelectedIndex(i);
			cbPlugs[i].setName("" + i);
			// combo[i].addMouseListener(this);
			cbPlugs[i].addItemListener(this);

			lblPlugs[i] = new JLabel("   " + cbPlugs[i].getSelectedItem().toString() + " =");

			p1.add(lblPlugs[i]);
			p1.add(cbPlugs[i]);
		}

		p3 = new JPanel();
		p3.setLayout(new BorderLayout());

		btClear = new JButton("Limpar");
		btClear.addActionListener(this);
		p3.add(btClear, "North");

		p5 = new JPanel();
		p5.setLayout(new GridLayout(1, 2));

		txtOrigin = new JTextField();
		txtOrigin.setEnabled(false);
		txtOrigin.setForeground(Color.BLACK);
		txtOrigin.setBackground(Color.WHITE);
		p5.add(txtOrigin);

		txtOutput = new JTextField();
		txtOutput.setEnabled(false);
		txtOutput.setForeground(Color.BLACK);
		txtOutput.setBackground(Color.WHITE);
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

		getContentPane().add(p9);
		getContentPane().add(p1);
		getContentPane().add(p2);

		enigma = new EnigmaMachine(generatePlugBoard());

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		boolean plugs = false;
		boolean start = false;
		boolean anel = false;

		for (JComboBox<String> jComboBox : cbPlugs) {
			if (jComboBox.equals(e.getSource()))
				plugs = !plugs;
		}
		
		for (JComboBox<String> jComboBox : cbRotorStart) {
			if (jComboBox.equals(e.getSource()))
				start = !start;
		}

		
		for (JComboBox<String> jComboBox : cbRotorAnel) {
			if (jComboBox.equals(e.getSource()))
				anel = !anel;
		}
		
		if (plugs) {
			changeOK = !changeOK;
			int index, otherIndex;
			JComboBox<String> choice;

			if (changeOK) {
				choice = ((JComboBox<String>) e.getItemSelectable());

				index = Integer.parseInt(choice.getName());
				otherIndex = choice.getSelectedIndex();
				if (cbPlugs[otherIndex].isEnabled()) {
					cbPlugs[otherIndex].setSelectedIndex(index);
					cbPlugs[otherIndex].setEnabled(false);
					cbPlugs[index].setEnabled(false);
					enigma.setPlugBoard(generatePlugBoard());
				} else {
					JOptionPane.showMessageDialog(this, "Plug ja ligado", "ERRO", JOptionPane.ERROR_MESSAGE);
					cbPlugs[index].setSelectedIndex(index);
					cbPlugs[index].setEnabled(true);

				}

				changeOK = !changeOK;
			}
		}
		else if(start)
		{
			enigma.setPositionRotor(cbRotorStart[0].getSelectedIndex(), cbRotorStart[1].getSelectedIndex(), cbRotorStart[2].getSelectedIndex());
		}
		else if(anel)
		{
			enigma.setPositionAnel(cbRotorAnel[0].getSelectedIndex(), cbRotorAnel[1].getSelectedIndex(), cbRotorAnel[2].getSelectedIndex());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btClear) {
			for (JComboBox<String> jComboBox : cbPlugs) {
				jComboBox.removeItemListener(this);
				jComboBox.setSelectedIndex(Integer.parseInt(jComboBox.getName()));
				jComboBox.setEnabled(true);
				jComboBox.addItemListener(this);
			}

			txtOrigin.setText("");
			txtOutput.setText("");
			enigma.reset();
			cbRotorStart[0].setSelectedIndex(0);
			cbRotorStart[1].setSelectedIndex(0);
			cbRotorStart[2].setSelectedIndex(0);
			cbRotorAnel[0].setSelectedIndex(0);
			cbRotorAnel[1].setSelectedIndex(0);
			cbRotorAnel[2].setSelectedIndex(0);

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
			txtOutput.setText(txtOutput.getText() + enigma.Encode(aux,cbRotorOrder[2].getSelectedIndex(),cbRotorOrder[1].getSelectedIndex(),cbRotorOrder[0].getSelectedIndex()));
			int[] positions = enigma.getPositionRotor();
			
			for (int i = 0; i < positions.length; i++) {
				cbRotorStart[i].setSelectedIndex(positions[i]);
			}
		}
		txtInput.setText("");
		txtInput.setFocusable(true);
	}

	private char[] generatePlugBoard() {
		char[] plug = new char[26];
		for (int i = 0; i < cbPlugs.length; i++) {
			plug[i] = cbPlugs[i].getSelectedItem().toString().charAt(0);
		}
		return plug;
	}
}
