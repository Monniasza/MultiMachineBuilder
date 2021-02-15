/**
 * 
 */
package mmb.WORLD.gui;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import mmb.WORLD.block.BlockEntry;
import mmb.WORLD.blocks.StringValue;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

/**
 * @author oskar
 *
 */
public class NewTextEditor extends JPanel {
	private JTextPane textPane;
	private JButton btnOk;
	private JButton btnCancel;
	public final String title;
	private final WorldWindow frame;

	/**
	 * Create the panel.
	 */
	public NewTextEditor(StringValue sv, BlockEntry block, WorldWindow frame) {
		this.frame = frame;
		title = "["+block.x+","+block.y+"]";
		setLayout(new MigLayout("", "[grow]", "[grow][21px][21px]"));
		
		textPane = new JTextPane();
		textPane.setText(sv.value);
		add(textPane, "cell 0 0,grow");
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(e -> {
			sv.value = textPane.getText();
			remove();
		});
		btnOk.setBackground(Color.GREEN);
		btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnOk, "cell 0 1,growx,aligny center");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> remove());
		btnCancel.setBackground(Color.RED);
		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnCancel, "cell 0 2,growx,aligny center");
	}
	private void remove() {
		frame.closeDialogWindow(this);
	}
}
