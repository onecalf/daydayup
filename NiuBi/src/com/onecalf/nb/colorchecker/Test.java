package com.onecalf.nb.colorchecker;

public class Test {
    public static void main(String[] args) throws Exception{
        String colorfile = "./colors.xml";


        ColorChecker checker = new ColorChecker();
        checker.setColorFilePath(colorfile);
        checker.printRepeatColor();
    }


}
