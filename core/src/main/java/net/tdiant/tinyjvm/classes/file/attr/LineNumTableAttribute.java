package net.tdiant.tinyjvm.classes.file.attr;

public class LineNumTableAttribute extends Attribute {

    private final Line[] lines;

    public LineNumTableAttribute(Line[] lines) {
        this.lines = lines;
    }

    public Line[] getLines() {
        return lines;
    }

    public static class Line {

        private final int startPc;
        private final int lineNum;

        public Line(int startPc, int lineNum) {
            this.startPc = startPc;
            this.lineNum = lineNum;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLineNum() {
            return lineNum;
        }
    }

}
