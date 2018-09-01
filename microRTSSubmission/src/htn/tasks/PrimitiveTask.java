package htn.tasks;

import java.util.LinkedList;
import java.util.List;
import util.ExtendedGameState;

/**
 *
 * @author marcel
 */
public abstract class PrimitiveTask extends Task {

    /**
     * Führt die Aufgabe aus, die durch diesen Task repräsentiert wird.
     * Wird aufgerufen, nachdemd er HTN vollständig aufgelöst wurde.
     * @param gs GameState erweitert um Ressourcenreservierungen.
     */
    public abstract void execute(ExtendedGameState gs);
    
    /**
     * Standardimplementierung. Fügt sich in die Liste vom aufgelösten HTN ein.
     * @param egs erlaubt es Units und Ressourcen zu reservierne. Wird nicht benutzt.
     * @return a List mit <code> this </code> als einziges Element.
     */
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        LinkedList<PrimitiveTask> ll = new LinkedList<>();
        ll.add(this);
        return ll;
    }

}
