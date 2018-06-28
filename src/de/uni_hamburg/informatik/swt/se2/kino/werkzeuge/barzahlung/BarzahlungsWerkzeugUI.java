package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarzahlungsWerkzeugUI
{
    private JDialog jd;
    private JTextField betragFeld;
    private JTextField bezahltFeld;
    private JTextField restbetragFeld;
    private JButton okButton;
    private JButton abbrechenButton;

    public BarzahlungsWerkzeugUI()
    {
        jd = new JDialog();
        jd.setSize(500, 500);
        jd.setVisible(true);
        jd.setModalityType(ModalityType.APPLICATION_MODAL);

        JPanel pane = new JPanel();
        BoxLayout boxlay = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(boxlay);

        pane.add(new JLabel("Preis"));

        betragFeld = new JTextField("0000");
        betragFeld.setEnabled(false);
        pane.add(betragFeld);

        pane.add(new JLabel("Bezahlt"));

        bezahltFeld = new JTextField("0000");
        bezahltFeld.setEnabled(true);
        pane.add(bezahltFeld);

        restbetragFeld = new JTextField("0000");
        restbetragFeld.setEnabled(false);
        pane.add(restbetragFeld);

        okButton = new JButton();
        okButton.setText("OK");
        okButton.setEnabled(false);
        pane.add(okButton);
        abbrechenButton = new JButton();
        abbrechenButton.setText("Abbrechen");
        pane.add(abbrechenButton);

        jd.getContentPane()
            .add(pane, BorderLayout.CENTER);
    }

    public JTextField getBetragFeld()
    {
        return betragFeld;
    }

    public JTextField getBezahltFeld()
    {
        return bezahltFeld;
    }

    public JTextField getRestbetragFeld()
    {
        return restbetragFeld;
    }

    public JButton getOkButton()
    {
        return okButton;
    }

    public JButton getAbbrechenButton()
    {
        return abbrechenButton;
    }

    public JDialog getJd()
    {
        return jd;
    }

}
