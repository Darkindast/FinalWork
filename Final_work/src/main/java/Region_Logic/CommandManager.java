
package Region_Logic;

import Command_Classes.*;
import java.util.HashMap;

/**
 * Класс CommandManager отвечает за хранение и управление списком доступных команд.
 * Команды используются для выполнения различных действий в регионах.
 */
public class CommandManager {
    
    /**
     * Список команд, где ключ — имя команды, значение — объект команды.
     */
    private HashMap<String, Command> commandList = new HashMap<>();

    /**
     * Конструктор CommandManager.
     * При создании автоматически заполняет список команд.
     */
    public CommandManager() {
        completeCommandList();
    }
    
    /**
     * Заполняет список команд предопределёнными командами.
     * В данном случае добавляет команды: FellTreeCommand, BuildHouseCommand, MakeFireCommand.
     */
    public void completeCommandList() {
        commandList.put(FellTreeCommand.getName(), new FellTreeCommand());
        commandList.put(BuildHouseCommand.getName(), new BuildHouseCommand());
        commandList.put(MakeFireCommand.getName(), new MakeFireCommand());
    }
    /**
     * Возвращает список команд.
     *
     * @return {@code HashMap<String, Command>} - карта команд по их названиям.
     */
    public HashMap<String, Command> getCommandList() {
        return commandList;
    }

}