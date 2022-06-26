package bo;

import bo.Custom.impl.ManageRoomBOImpl;
import bo.Custom.impl.ManageStudentBOImpl;
import bo.Custom.impl.RegisterStudentBOImpl;
import bo.Custom.impl.ReservationBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes {
        LOGIN_USER,MANAGE_ROOM,MANAGE_STUDENT,REGISTER_STUDENT,RESERVATION_DETAILS,
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
//            case LOGIN_USER:
//                return new LoginBOImpl();
            case MANAGE_ROOM:
                return new ManageRoomBOImpl();
            case MANAGE_STUDENT:
                return new ManageStudentBOImpl();
            case REGISTER_STUDENT:
                return new RegisterStudentBOImpl();
            case RESERVATION_DETAILS:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
