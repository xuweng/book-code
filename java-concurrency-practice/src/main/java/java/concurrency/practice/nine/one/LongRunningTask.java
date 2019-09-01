package java.concurrency.practice.nine.one;

import jdk.internal.org.objectweb.asm.tree.analysis.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Binding a long-running task to a visual component.
 */
public class LongRunningTask {
    public void addActionListener(ActionListener actionListener) {
        ExecutorService backgroundExec = Executors.newCachedThreadPool();
        LongRunningTask button = null;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundExec.execute(new Runnable() {
                    @Override
                    public void run() {
                        doBigComputation();
                    }

                    private void doBigComputation() {
                    }
                });
            }
        });
    }

    /**
     * Long-running task with user feedback.
     *
     * @param actionListener
     */
    public void addActionListener2(ActionListener actionListener) {
        AbstractButton button = null;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(false);
                AbstractButton label = null;
                label.setText("busy");
                Frame<V> backgroundExec = null;
                backgroundExec.execute(new Runnable() {
                    public void run() {
                        try {
                            doBigComputation();
                        } finally {
                            GuiExecutor.instance().execute(new Runnable() {
                                public void run() {
                                    button.setEnabled(true);
                                    label.setText("idle");
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void doBigComputation() {
    }
}
