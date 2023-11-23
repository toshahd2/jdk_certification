package ru.anton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        String inputData;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = 1;
        HashMap<Integer, Boolean> results = new HashMap<>();
        while (true) {
            int prizeDoor = prizeDoor();

            System.out.println("Новая игра №" + i);
            System.out.println("Перед вами двери под номером '1', '2' и '3'. За одной дверью спрятан автомобиль, за двумя другими - козы.");
            System.out.println("Дверь под каким номером выбираете('1', '2' и '3')?");
            String stringDoor = reader.readLine();
            int userDoor = Integer.parseInt(stringDoor);

            int otherDoor = otherDoor(prizeDoor,userDoor);
            goatDoor(prizeDoor,userDoor,otherDoor);

            System.out.println("Вы можете поменять дверь " + userDoor + " на дверь " + otherDoor + ". Будете менять('да' или 'нет')?");
            String doorChange = reader.readLine();
            if (doorChange.equals("yes") || doorChange.equals("y") || doorChange.equals("да")) {
                userDoor = otherDoor;
            }

            if (userDoor == prizeDoor) {
                System.out.println("Вы победили! Машина ваша!");
                results.put(i,true);
            } else {
                System.out.println("Увы - коза! Вы проиграли!");
                results.put(i,false);
            }


            System.out.println("\nХотите еще раз сыграть?");
            System.out.println("Введите 'да' или 'нет': ");
            inputData = reader.readLine();
            if (inputData.equals("no") || inputData.equals("n") || inputData.equals("нет")) {
                logger.info(String.valueOf(results));
                reader.close();
                break;
            } else i++;
        }

    }

    private static void goatDoor(int prizeDoor, int userDoor, int otherDoor) {
        Random random = new Random();
        int goatDoor = prizeDoor;
        while (goatDoor == prizeDoor || goatDoor == userDoor || goatDoor == otherDoor){
            goatDoor = random.nextInt(3)+1;
        }
        String goat = Integer.toString(goatDoor);
        System.out.println("Откроем дверь под номером " + goat + ". Там коза!");
    }

    private static int prizeDoor() {
        Random random = new Random();
        int prizeDoor = random.nextInt(3)+1;
        return prizeDoor;
    }

    private static int otherDoor(int prizeDoor, int userDoor) {
        Random random = new Random();
        int otherDoor = userDoor;

        while (otherDoor == userDoor || otherDoor == prizeDoor){
            otherDoor = random.nextInt(3)+1;
        }
        return otherDoor;
    }


}