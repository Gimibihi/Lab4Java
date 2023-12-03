package Lab4Bijeikis;

import laborai.demo.Timekeeper;
import laborai.gui.MyException;
import laborai.studijosktu.HashType;
import laborai.studijosktu.MapKTUx;
import laborai.studijosktu.TgMapx;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * @author eimutis
 */
public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finishCommand";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("Lab4Bijeikis.Gui.messages");

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;

    private final String[] TYRIMU_VARDAI = {"add0.25", "add0.5", "add0.75","add0.95", "rem0.25","rem0.5","rem0.75", "rem0.95", "get0.25", "get0.5", "get0.75", "get0.95"};
    private final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private final TgMapx<String, Kompiuteris> kompAtvaizdis
            = new TgMapx<>(new String(), new Kompiuteris(), 10, 0.25f, HashType.DIVISION);
    private final TgMapx<String, Kompiuteris> kompAtvaizdis2
            = new TgMapx<>(new String(), new Kompiuteris(), 10, 0.5f, HashType.DIVISION);
    private final TgMapx<String, Kompiuteris> kompAtvaizdis3
            = new TgMapx<>(new String(), new Kompiuteris(), 10, 0.75f, HashType.DIVISION);
    private final TgMapx<String, Kompiuteris> kompAtvaizdis4
            = new TgMapx<>(new String(), new Kompiuteris(), 10, 0.95f, HashType.DIVISION);
    private final Queue<String> chainsSizes = new LinkedList<>();

    public GreitaveikosTyrimas() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
    }

    public void pradetiTyrima() {
        try {
            SisteminisTyrimas();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void SisteminisTyrimas() throws InterruptedException {
        try {
            chainsSizes.add(MESSAGES.getString("msg4"));
            chainsSizes.add("   kiekis      " + TYRIMU_VARDAI[0] + "   " + TYRIMU_VARDAI[1]);
            for (int k : TIRIAMI_KIEKIAI) {
                Kompiuteris[] autoArray = KompGamyba.gamintiKompiuterius(k);
                String[] autoIdArray = KompGamyba.gamintiKompIDs(k);
                kompAtvaizdis.clear();
                kompAtvaizdis2.clear();
                kompAtvaizdis3.clear();
                kompAtvaizdis4.clear();
                tk.startAfterPause();
                tk.start();

                for (int i = 0; i < k; i++) {
                    kompAtvaizdis.put(autoIdArray[i], autoArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[0]);

                String str = "   " + k + "          " + kompAtvaizdis.getMaxChainSize();
                for (int i = 0; i < k; i++) {
                    kompAtvaizdis2.put(autoIdArray[i], autoArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[1]);

                str += "         " + kompAtvaizdis2.getMaxChainSize();

                for (int i = 0; i < k; i++) {
                    kompAtvaizdis3.put(autoIdArray[i], autoArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[2]);

                str += "         " + kompAtvaizdis3.getMaxChainSize();

                for (int i = 0; i < k; i++) {
                    kompAtvaizdis4.put(autoIdArray[i], autoArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[3]);

                str += "         " + kompAtvaizdis4.getMaxChainSize();
                chainsSizes.add(str);

                for (String s : autoIdArray) {
                    kompAtvaizdis.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[4]);

                for (String s : autoIdArray) {
                    kompAtvaizdis2.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[5]);

                for (String s : autoIdArray) {
                    kompAtvaizdis3.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[6]);

                for (String s : autoIdArray) {
                    kompAtvaizdis4.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[7]);

                for (String s : autoIdArray) {
                    kompAtvaizdis.get(s);
                }
                tk.finish(TYRIMU_VARDAI[8]);

                for (String s : autoIdArray) {
                    kompAtvaizdis2.get(s);
                }
                tk.finish(TYRIMU_VARDAI[9]);

                for (String s : autoIdArray) {
                    kompAtvaizdis3.get(s);
                }
                tk.finish(TYRIMU_VARDAI[10]);

                for (String s : autoIdArray) {
                    kompAtvaizdis4.get(s);
                }
                tk.finish(TYRIMU_VARDAI[11]);
                tk.seriesFinish();
            }

            StringBuilder sb = new StringBuilder();
            chainsSizes.stream().forEach(p -> sb.append(p).append(System.lineSeparator()));
            tk.logResult(sb.toString());
            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            tk.logResult(e.getMessage());
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
