
package Command_Classes;

import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий результат выполнения действия команды.
 * Содержит информацию о результате, статусе, изменении инвентаря, месте действия и необходимости удаления объекта.
 */
public class ActionResult {

    /**
     * Сообщение о результате действия.
     */
    private String message;

    /**
     * Статус выполнения действия (true — успешно, false — неудачно).
     */
    private boolean status;

    /**
     * Указывает, был ли изменён инвентарь в результате действия.
     */
    private boolean isInventoryChanged;

    /**
     * Объект интереса, в котором происходило действие.
     */
    private ObjectInterest placeOfAction;

    /**
     * Флаг, указывающий, необходимо ли удалить объект из региона.
     */
    private boolean deleteObjectFromRegion = false;

    /**
     * Устанавливает сообщение результата действия.
     *
     * @param message строка с описанием результата
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Устанавливает статус выполнения действия.
     *
     * @param status true — действие выполнено успешно, false — иначе
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Устанавливает объект интереса, в котором происходило действие.
     *
     * @param obj объект интереса
     */
    public void setObjectInterest(ObjectInterest obj) {
        this.placeOfAction = obj;
    }

    /**
     * Возвращает сообщение результата действия.
     *
     * @return строка с описанием результата
     */
    public String getMessage() {
        return message;
    }

    /**
     * Возвращает статус выполнения действия.
     *
     * @return true, если действие успешно, иначе — false
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Возвращает объект интереса, в котором происходило действие.
     *
     * @return объект интереса
     */
    public ObjectInterest getPlaceOfAction() {
        return placeOfAction;
    }

    /**
     * Возвращает полную строку результата действия с указанием объекта интереса.
     *
     * @return строка полного результата действия
     */
    public String getCompleteResult() {
        return message + " в объекте интереса " + placeOfAction.getObjectType();
    }

    /**
     * Возвращает флаг, указывающий, нужно ли удалить объект из региона.
     *
     * @return true, если нужно удалить объект, иначе — false
     */
    public boolean isDeleteObjectFromRegion() {
        return deleteObjectFromRegion;
    }

    /**
     * Устанавливает флаг удаления объекта из региона.
     *
     * @param deleteObjectFromRegion true — объект будет удалён, false — останется
     */
    public void setDeleteObjectFromRegion(boolean deleteObjectFromRegion) {
        this.deleteObjectFromRegion = deleteObjectFromRegion;
    }
}