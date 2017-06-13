package typed.impl.literal;


import typed.api.Tree;
import typed.api.lexical.SyntaxToken;

import javax.annotation.Nullable;

public class SyntaxList<T extends Tree> {

    private T element;
    private SyntaxToken commaToken;
    private SyntaxList<T> next;

    public SyntaxList(T element, @Nullable SyntaxToken commaToken, @Nullable SyntaxList<T> next) {
        this.element = element;
        this.commaToken = commaToken;
        this.next = next;
    }

    public T element() {
        return element;
    }

    @Nullable
    public SyntaxToken commaToken() {
        return commaToken;
    }

    @Nullable
    public SyntaxList next() {
        return next;
    }
}
