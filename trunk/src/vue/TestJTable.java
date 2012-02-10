package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import modèle.Livre;

public class TestJTable extends JFrame {
	
	public TestJTable() {
		super();
		
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(500, 400);
		
		JPanel tabPanel = new JPanel(new BorderLayout());
		
		Object[][] rowData = {{"aaa","bbbb","ccc","dddddd", "eeeee"},
							{"aaa","bbbb","ccc","dddddd", "eeeee"},
							{"aaa","bbbb","ccc","dddddd", "eeeee"}};
		final JTable table1 = new JTable(rowData, Livre.getLabelValues());
		
		tabPanel.add(table1.getTableHeader(), BorderLayout.PAGE_START);
		tabPanel.add(table1, BorderLayout.CENTER);
		
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table1.getSelectedRow();
				int column = table1.getSelectedColumn();
				
				
				System.out.println("Ligne :"+row+" | colonne : "+column);
			}
		});

		this.add(tabPanel);
	}
}
