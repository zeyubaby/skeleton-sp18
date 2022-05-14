public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> characterDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            characterDeque.addLast(word.charAt(i));
        }
        return characterDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> characterDeque = wordToDeque(word);
        if (characterDeque.size() == 0 || characterDeque.size() == 1) {
            return true;
        }
        while (characterDeque.size() > 1) {
            char first = characterDeque.removeFirst();
            char last = characterDeque.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> characterDeque = wordToDeque(word);
        if (characterDeque.size() == 0 || characterDeque.size() == 1) {
            return true;
        }
        while (characterDeque.size() > 1) {
            char first = characterDeque.removeFirst();
            char last = characterDeque.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        String word = "aaaabbb";
        palindrome.wordToDeque(word);
    }

}
