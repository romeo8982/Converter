package com.example.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConverterApplication is a controller class which contain main() method of application.
 *
 * @author Jeremiasz Romejko
 */


@SpringBootApplication
@RestController
public class ConverterApplication {

    /**
     * Start spring application
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(ConverterApplication.class, args);
    }

    /**
     * solution form https://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java
     * "TreeMap is sorting the keys by natural order. If there is an exact match return the associated roman symbol,
     * else concatenate the roman symbol associated by the greatest key less than the given number to the one returned
     * by recursively calling the function using the current number subtracting the previous greatest key found."
     *
     * @param number number to convert.
     * @return text string containing rome numeric representation
     */
    private String toRoman(int number) {
        int floorKey = RomeMap.map.floorKey(number);
        if (number == floorKey)
            return RomeMap.map.get(number);
        return RomeMap.map.get(floorKey) + toRoman(number - floorKey);
    }


    /**
     * convert gets @params from user by @RequestParam on index.html. Its main goal is to check user input,
     * it is also responsible to convert int to hex it returns hex complement code for hexType option
     * and rome representation for romeType option.
     *
     * @param arabicNumber decimal integer number
     * @param radioType    conversion type hexType or romeType
     * @return converted number or error message
     */

    @PostMapping("/convert")
    public String convert(@RequestParam String arabicNumber, @RequestParam("radioType") String radioType) {

        try {
            String output;
            int number;
            if (!arabicNumber.isEmpty())
                number = Integer.parseInt(arabicNumber);
            else {
                output = "Nie podano liczby do konwersji.";
                return output;
            }
            if (radioType.equals("hexType"))
                output = Integer.toHexString(number);
            else if (radioType.equals("romeType")) {
                if (number >= 0)
                    output = toRoman(number);
                else
                    output = "Jeśli chcesz skorzstać z konwertera liczb rzyskich wprowadź dodatnią wartość.";
            } else
                output = "Nie wprowadzono poprawnego typu konwersji.";
            return output;
        } catch (NumberFormatException e) {
            return "Nie wprowadzono liczby całkowitej z przedziału <-2^31, 2^31-1>.";
        }
    }
}