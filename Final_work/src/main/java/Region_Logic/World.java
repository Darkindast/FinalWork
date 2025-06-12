/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Region_Logic;

import Regions.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Andrey
 */
public class World {

    RegionManager regionManager = new RegionManager();
    CommandManager commandManager = new CommandManager();
    Player player = new Player();
    Scanner scanner;

    public World() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean doActions = true;
        System.out.println("Введите количество регионов для генерации мира");
        System.out.println("Ввведите количество регионов Тундра: ");
        int countTundra = ChoiceCheck();
        System.out.println("Ввведите количество регионов Пустыня: ");
        int countDesert = ChoiceCheck();
        System.out.println("Ввведите количество регионов Смешанный лес: ");
        int countMildClimate = ChoiceCheck();
        regionManager.generateRegions(countTundra, countDesert, countMildClimate);
        player.setCurrentRegion(regionManager.getRegion(0));
        while (doActions) {
            System.out.println("----------------");
            System.out.print("Ваш текущий регион : " + player.getCurrentRegion().getUniqueName());
            System.out.println("Доступные команды для взаимодействия с ОИ: " + commandManager.getCommandList().keySet());
            System.out.println("Если вы хотите переместится в другой регион, введите: move");
            System.out.println("Если вы хотите посмотреть содержимое инвентаря, введите: checkInventory");
            System.out.println("Если вы хотите завершить игру, введите: exit");
            System.out.println("Введите команду из предложенных: ");
            String actionName = scanner.nextLine();
            if ("move".equals(actionName)) {
                moveToRegion();
            }
            else if ("checkInventory".equals(actionName)) {
                System.out.println("Количество бревен сейчас: " + player.getInventory().getNumLogs());
            }
            else if("exit".equals(actionName)){
                System.out.println("Спасибо за игру!");
                doActions = false;
            }
            else{  
            System.out.println("Список ОИ в текущем регионе: ");
            for (ObjectInterest object : player.getCurrentRegion().getObjectsInterestList()) {
                System.out.println(object.getObjectType());
            }
            scanner.nextLine();
            System.out.println("Введите номер объекта интереса из предложенных: ");
            int objectIndex = scanner.nextInt();
            System.out.println(player.makeAction(player.getCurrentRegion().getObjectsInterestList().get(objectIndex-1), commandManager.getCommandList().get(actionName)).getCompleteResult());
        }
        }
    }

    public void moveToRegion() {
        ArrayList<BaseRegion> availableRegions = regionManager.checkAvailableRegions(player.getCurrentRegion());
        System.out.println("Доступные регионы для перемещения: ");
        for (BaseRegion region : availableRegions) {
            System.out.print(regionManager.getRegionPosition(region));
            System.out.println("" + region.getUniqueName());
        }
        System.out.println("Всего регионов для выбора:" + availableRegions.size());
        System.out.println("Выберите номер региона, куда хотите переместится: ");
        int regionIndex = scanner.nextInt();
        player.setCurrentRegion(availableRegions.get(regionIndex-1));
        System.out.println(player.getCurrentRegion());
    }
    

    public static int ChoiceCheck() {
    Scanner in = new Scanner(System.in);
    int choice = -1;

    while (true) {
        try {
            choice = in.nextInt();
            if (choice < 0) {
                System.out.println("Error! Negative number");
            } else {
                break;  // корректный ввод, выходим из цикла
            }
        } catch (InputMismatchException e) {
            System.out.println("Error! Wrong type");
            in.next();  // очищаем неверный ввод из потока
        }
    }
    return choice;
    }
}