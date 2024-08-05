
import java.util.Scanner;
import java.util.ArrayList;

public class Truth_Table {
  public static void main(String args[]) {

    Scanner scanner = new Scanner(System.in); // Getting the expression from the user
    System.out.println("enter the expression: ");
    String expression = scanner.next();

    // Storing the user input into an arraylist of characters
    char[] expressionChars = expression.toCharArray();

    int variableCount = 0;
    String variables = "";
    boolean isTrue = false;

    // counting the number of logical variables
    for (char c : expressionChars) {
      if (c != '&' && c != '|' && c != '!' && c != ' ' && c != '+' && c != '>' && c != '~') {
        variableCount++;
        variables += c;
      }
    }
    // printing the first row of the table
    char[] e = variables.toCharArray();
    for (char f : e) {
      System.out.print(f + "\t");

    }
    System.out.println(expression);
    System.out.println("__________________________________");
    // getting the number of rows
    double numRow = Math.pow(2, variableCount);
    ArrayList<String> storeMinMax = new ArrayList<String>();
    for (int i = 0; i < numRow; i++) { // printing possible truth values of each row

      ArrayList<String> cities1 = new ArrayList<String>();
      for (int p = 0; p < variables.length(); p++) {
        cities1.add(String.valueOf(variables.charAt(p)));
      }

      char result = ' ';
      int index = 0;
      ArrayList<Character> list = new ArrayList<Character>();
      ArrayList<Character> resulting = new ArrayList<Character>();
      for (char c : expressionChars) {
        list.add(c);
      }
      // printing all possible truth table values
      for (int j = variableCount - 1; j >= 0; j--) { //
        result = (i & (1 << j)) == 0 ? 'F' : 'T';
        resulting.add(result);
        list.set(index, result);

        System.out.print(result + "\t");
        index += 2;
        if (index == list.indexOf('!'))
          index++;
      }

      for (char c : expressionChars) {
        if (c == '!') {
          if (list.get(list.indexOf('!') + 1) == 'F') {
            isTrue = true;
            list.set(list.indexOf('!') + 1, 'T');
          } else if (list.get(list.indexOf('!') + 1) == 'T') {
            isTrue = false;
            list.set(list.indexOf('!') + 1, 'F');

          }
        }
      }
      if (list.contains('!')) {
        list.set(list.indexOf('!'), list.get(list.indexOf('!') + 1));
      }

      for (char c : expressionChars) {
        if (c == '&') {
          if (list.get(list.indexOf(c) - 1) == 'T' && list.get(list.indexOf(c) + 1) == 'T') {
            isTrue = true;
            list.set(list.indexOf(c) + 1, 'T');
          } else {
            isTrue = false;
            list.set(list.indexOf(c) + 1, 'F');
          }
          list.set(list.indexOf('&'), '1');
        } else if (c == '|') {
          if ((list.get(list.indexOf(c) - 1) == 'F' && list.get(list.indexOf(c) + 1) == 'F')) {
            isTrue = false;
            list.set(list.indexOf(c) + 1, 'F');
          } else {
            isTrue = true;
            list.set(list.indexOf(c) + 1, 'T');
          }
          list.set(list.indexOf('|'), '1');
        } else if (c == '>') {
          if (list.get(list.indexOf('>') - 1) == 'T' && list.get(list.indexOf('>') + 1) == 'F') {
            list.set(list.indexOf(c) + 1, 'F');
            isTrue = false;
          } else {
            list.set(list.indexOf(c) + 1, 'T');
            isTrue = true;
          }
        } else if (c == '+') {
          if (list.get(list.indexOf('+') - 1) != list.get(list.indexOf('+') + 1)) {
            list.set(list.indexOf(c) + 1, 'T');
            isTrue = true;
          } else if (list.get(list.indexOf('+') - 1) == list.get(list.indexOf('+') + 1)) {
            isTrue = false;
            list.set(list.indexOf(c) + 1, 'F');
          }
        } else if (c == '~')
          if (list.get(list.indexOf('~') - 1) == list.get(list.indexOf('~') + 1)) {
            list.set(list.indexOf(c) + 1, 'T');
            isTrue = true;
          } else {
            list.set(list.indexOf(c) + 1, 'F');
            isTrue = false;
          }
      }
      String not = "!";
      int num = 0;
      int mum = 0;
      for (char c : resulting) {
        if (isTrue == true && c == 'F') {
          cities1.set(resulting.indexOf(c) + num, not.concat(cities1.get(resulting.indexOf(c) + num)));

        } else if (isTrue == false && c == 'T') {
          cities1.set(resulting.indexOf(c) + mum, not.concat(cities1.get(resulting.indexOf(c) + mum)));
          mum++;
        }
      }

      for (int q = 1; q < cities1.size();) {
        if (isTrue == true)
          cities1.add(q, "∧");
        else if (isTrue == false)
          cities1.add(q, "V");
        q += 2;
      }
      StringBuilder sb = new StringBuilder();
      for (String element : cities1) {
        sb.append(element + " ");
      }
      String joinedString = sb.toString();
      System.out.print(isTrue + "\t");
      System.out.println();
      storeMinMax.add(joinedString);
    }
    System.out.print("Max Term: ");
    int lp = 0, rp = 0;
    for (String element : storeMinMax) {
      if (element.contains("∧")) {
        if (lp != storeMinMax.size() - 1) {
          System.out.print("(" + element + ")" + " V ");
        } else {
          System.out.print("(" + element + ")");
        }
        lp++;
      }
    }
    System.out.println();
    scanner.close();
    System.out.print("Min Term: ");
    for (String element : storeMinMax) {
      if (element.contains("V")) {
        if (rp != storeMinMax.size() - 1) {
          System.out.print("(" + element + ")" + " ^ ");
        } else {
          System.out.print("(" + element + ")");
        }
        rp++;
      }
    }
  }

}
