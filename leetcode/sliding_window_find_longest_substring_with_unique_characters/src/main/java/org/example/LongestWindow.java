package org.example;
import java.util.HashSet;

public class LongestWindow {
    private final HashSet<Character> uniqueCharacters = new HashSet<Character>();
    private int maxUniqueCharacters;
    private String input;
    private int currentPosition;
    private int characterCounter = 0;
    private int longestUniqueCharacterLength = 0;

    public int findLongestSubstring(String input, int maxUniqueCharacters) {
        this.input = input;
        this.maxUniqueCharacters = maxUniqueCharacters;
        this.currentPosition = 0;
        this.longestUniqueCharacterLength = 0;
        this.characterCounter = 0;
        moveForward();
        return longestUniqueCharacterLength;
    }

    private void moveWindow() {
        if (this.characterCounter > longestUniqueCharacterLength) {
            this.longestUniqueCharacterLength = this.characterCounter;
        }
        uniqueCharacters.clear();
        characterCounter = 0;
    }

    private int addUniqueCharacter(char character) {
        uniqueCharacters.add(character);
        return uniqueCharacters.size();
    }

    private void moveForward() {
        while(currentPosition < input.length()) {
            var uniqueCharacters = addUniqueCharacter(input.charAt(currentPosition));
            if(uniqueCharacters > maxUniqueCharacters) {
                moveWindow();
                incrementCounter();
            }
            else {
                incrementCounter();
            }
            currentPosition++;
        }
    }

    private void incrementCounter() {
        this.characterCounter++;
    }
}
