package fr.an.tests.javafxwhiteapp.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class DrawingElement {
    /**
     * Visitor design pattern
     * implement in sub-class, to call <code> visitor.caseXX(this); </code>
     */
    public abstract void accept(DrawingElementVisitor visitor);
}
