package com.gmail.maxkhrebtov;

import com.gmail.maxkhrebtov.mirror.MirrorService;
import com.gmail.maxkhrebtov.mirror.MirrorServiceImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] arg) {
        MirrorService mirrorService = new MirrorServiceImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text to be mirrored: ");
        String text = scanner.nextLine();

        String mirroredText = mirrorService.mirrorText(text);

        System.out.println("Mirrored text: " + mirroredText);
    }
}
