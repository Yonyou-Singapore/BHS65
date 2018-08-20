package nc.ui.bhs.pub.dialog;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


/**
 * EXCEL�ļ�ѡ�� �Ի���   shihy 
 */
public class FileDialog {

	JFileChooser jFileChooser = new JFileChooser(); 
	
	public FileDialog() {
		super();
	}
	
	public boolean show(){
		jFileChooser.removeChoosableFileFilter(jFileChooser.getAcceptAllFileFilter());
		jFileChooser.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				if(f.getName().endsWith(".xls")||f.isDirectory()){
			          return true;
			        }
			        return false;

			}
			public String getDescription() {
				// TODO �Զ����ɷ������
				return "�����ļ�(*.xls)";
			}
		});
		
		return JFileChooser.APPROVE_OPTION == jFileChooser.showOpenDialog(null);
	}
	
	public String getFilePath(){
		return jFileChooser.getSelectedFile().getPath();
	}

}

