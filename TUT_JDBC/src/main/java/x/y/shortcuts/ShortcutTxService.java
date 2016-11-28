package x.y.shortcuts;

public class ShortcutTxService {

    @OrderTx
    public void setSomething(String name) { throw new UnsupportedOperationException(); }

    @AccountTx
    public void doSomething() { throw new UnsupportedOperationException(); }
}