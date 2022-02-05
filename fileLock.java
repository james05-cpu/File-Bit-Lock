  package lock;

        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.File;


        public class fileLock {
            public static void main(String[] args) {
               new myWindow();
            }
        }
        class myWindow extends JFrame implements ActionListener {
        JPanel panel;
        JLabel mode,k,val1,val2;
        JTextField keyField;
        JComboBox transformation,alg;
        JButton selectFile, saveAs, encryp , decryp;
        String transfms;
        String TRANSFORMATIONS[];
        String algos;
        String ALGORITHMS[];
        File filein=null;
        File fileout=null;
        SecMethods methods=null;
        myWindow(){
            methods=new SecMethods();
            algos ="AES," +
                    "Rijndael," +
                    "DES," +
                    "TripleDES," +
                    "RSA" ;
            ALGORITHMS= algos.split(",");
            transfms ="AES/CBC/NoPadding," +
                    "AES/CBC/PKCS5Padding," +
                    "AES/ECB/NoPadding," +
                    "AES/ECB/PKCS5Padding," +
                    "DES/CBC/NoPadding," +
                    "DES/CBC/PKCS5Padding," +
                    "DES/ECB/NoPadding," +
                    "DES/ECB/PKCS5Padding," +
                    "DESede/CBC/NoPadding," +
                    "DESede/CBC/PKCS5Padding," +
                    "DESede/ECB/NoPadding," +
                    "DESede/ECB/PKCS5Padding," +
                    "RSA/ECB/PKCS1Padding," +
                    "RSA/ECB/OAEPWithSHA-1AndMGF1Padding," +
                    "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
            TRANSFORMATIONS = transfms.split(",");
           transformation =new JComboBox(TRANSFORMATIONS);
           alg=new JComboBox(ALGORITHMS);
           selectFile =new JButton("Select");
           saveAs =new JButton("Save as");
            encryp =new JButton("Encrypt");
            decryp=new JButton("Decrypt");
            mode=new JLabel("Params");
            k=new JLabel("Key");
            keyField =new JTextField(40);
             val1=new JLabel("No file selected");
             val1.setBounds(190,190,200,30);
            val2=new JLabel("No file selected");
            val2.setBounds(190,240,300,30);

            transformation.setBounds(260,90,250,30);
            alg.setBounds(140,90,100,30);
           mode.setBounds(30,90,100,30);
            k.setBounds(30,140,50,30);
            keyField.setBounds(80,140,420,30);
           selectFile.setBounds(80,190,100,30);
           saveAs.setBounds(80,240,100,30);
           encryp.setBounds(150,310,100,30);
            decryp.setBounds(270,310,100,30);

            selectFile.addActionListener(this);
            encryp.addActionListener(this);
            saveAs.addActionListener(this);
            decryp.addActionListener(this);

            selectFile.setBorder(null);
            saveAs.setBorder(null);

            panel=new JPanel();
            panel.setBounds(0,0,550,500);
            panel.setLayout(null);
            panel.add(transformation);
            panel.add(alg);
            panel.add(mode);
            panel.add(k);
            panel.add(keyField);
            panel.add(selectFile);
            panel.add(saveAs);
            panel.add(val1);
            panel.add(val2);
            panel.add(encryp);
            panel.add(decryp);
            add(panel);
            setLayout(null);
            setSize(550,500);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == selectFile) {
                    JFileChooser opener = new JFileChooser();
                    int resp = opener.showOpenDialog(null);
                    if (resp == JFileChooser.APPROVE_OPTION) {
                        filein = new File(opener.getSelectedFile().getAbsolutePath());
                        val1.setText(filein.getName());
                    }
                }
                if (e.getSource() == saveAs) {
                    JFileChooser chooser = new JFileChooser();
                    int response = chooser.showSaveDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        fileout = new File(chooser.getSelectedFile().getAbsolutePath());
                        val2.setText(fileout.getName());
                    }
                }
                if (e.getSource()==encryp){
                    if (val1.getText().equals("No file selected")) {
                        JOptionPane.showMessageDialog(null, "no file to encrypt");
                        return;
                    }
                    if (val2.getText().equals("No file selected")) {
                        JOptionPane.showMessageDialog(null,"save location is blank");
                        return;
                    }
                    if (keyField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Key is empty");
                        return;
                    }
                    else {
                            methods.lockFile((String) alg.getSelectedItem(), (String) transformation.getSelectedItem(), keyField.getText(), filein, fileout);
                        }
                    }
                if (e.getSource()==decryp){
                    if (val1.getText().equals("No file selected")) {
                        JOptionPane.showMessageDialog(null, "no file to encrypt");
                        return;
                    }
                    if (val2.getText().equals("No file selected")) {
                        JOptionPane.showMessageDialog(null,"save location is blank");
                        return;
                    }
                    if (keyField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Key is empty");
                        return;
                    }
                    else {
                            methods.unlockFile((String) alg.getSelectedItem(),(String) transformation.getSelectedItem(), keyField.getText(),filein,fileout);
                        }
                    }
                }
            }
