package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;

public class BarzahlungsWerkzeug
{
    private BarzahlungsWerkzeugUI barUI;
    private int preis = 0;
    private boolean success = false;
    private boolean isFinished = false;

    private PlatzVerkaufsWerkzeug _pvw;
    private Vorstellung _vorstellung;

    public BarzahlungsWerkzeug(int preis, PlatzVerkaufsWerkzeug pvw, Vorstellung vorstellung)
    {
        this._pvw = pvw;
        this._vorstellung = vorstellung;

        this.preis = preis;
        barUI = new BarzahlungsWerkzeugUI();
        barUI.getBetragFeld()
            .setText(preis + "");
        barUI.getBezahltFeld()
            .getDocument()
            .addDocumentListener(new DocumentListener()
            {
                public void changedUpdate(DocumentEvent e)
                {
                    changedBetragTextFeld();
                }

                public void removeUpdate(DocumentEvent e)
                {
                    changedBetragTextFeld();
                }

                public void insertUpdate(DocumentEvent e)
                {
                    changedBetragTextFeld();
                }
            });

        barUI.getAbbrechenButton()
            .addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    success = false;
                    barUI.getJd()
                        .dispose();
                }
            });

        barUI.getOkButton()
            .addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    barUI.getJd()
                        .dispose();
                    _pvw.verkaufePlaetze(_vorstellung);
                }
            });
    }

    public void changedBetragTextFeld()
    {
        barUI.getOkButton()
            .setEnabled(false);
        try
        {
            int eingabebetrag = Integer.parseInt(barUI.getBezahltFeld()
                .getText());
            if (eingabebetrag <= 0)
            {
                //JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 0", "Error Massage", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int restbetrag = preis - eingabebetrag;
                barUI.getRestbetragFeld()
                    .setText(restbetrag + "");
                if (restbetrag <= 0)
                {
                    barUI.getOkButton()
                        .setEnabled(true);
                }
            }
        }
        catch (NumberFormatException e)
        {
            // keine Zahl sondern int wurde eingegeben
        }
    }

    public boolean getSuccess()
    {
        return success;
    }

    public boolean isFinished()
    {
        return isFinished;
    }

    public JButton getOkButton()
    {
        return barUI.getOkButton();
    }

    public JDialog getJd()
    {
        return barUI.getJd();
    }
}
