package htn.condition;

/**
 * Klasse zum testen der HTN-Funktionalität.
 * Ermöglicht das an und abschalten einzelne Methoden abzuschalten. Sollte im Normalbetrieb nicht benutzt werden.
 * @author marcel
 */
@Deprecated
public class DebugCondition extends Condition {
    
    private boolean state;
    
    @Deprecated
    public DebugCondition(boolean state) {
        this.state = state;
    }
    
    @Override
    @Deprecated
    public boolean conditionFulfilled(Object o) {
        /* o wird nicht verwendet */
        return state;
    }
    
}
