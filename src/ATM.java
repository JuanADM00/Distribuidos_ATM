import java.util.Scanner;

public class ATM {

    protected static ConexionDB instancia;

    public static void main(String[] args) {

        instancia = new ConexionDB();
        Scanner s = new Scanner(System.in);
        boolean control = true, loged = true;
        String numerodecuenta = "", contraseña = "";
        int opcion = -1, monto = 0;
        while (control) {
            System.out.println("Ingresa número de cuenta");
            numerodecuenta = s.nextLine();
            if (instancia.login(numerodecuenta)) {
                control = false;
            } else {
                System.out.println("El número marcado es inválido o no está registrado");
            }
        }
        while (loged) {
            System.out.println("1) Abono.\n2) Retiro.\n3) Consulta de saldo.\n4) Transferencia.\n0) Salir.");
            control = true;
            while (control) {
                try {
                    opcion = Integer.parseInt(s.nextLine());
                    if ((opcion > -1) && (opcion < 5)) {
                        control = false;
                    } else {
                        System.out.println("Opción inexistente. Inténtalo de nuevo.");
                    }
                } catch (Exception e) {
                    System.out.println("Formato inválido. Inténtalo de nuevo.");
                }
            }
            switch (opcion) {
                case 1:
                    monto = 0;
                    contraseña = "";
                    control = true;
                    while (control) {
                        System.out.println("CANTIDAD A ABONAR: ");
                        try {
                            monto = Integer.parseInt(s.nextLine());
                            System.out.println("CONTRASEÑA: ");
                            contraseña = s.nextLine();
                            if (monto > -1 && instancia.payment(numerodecuenta, contraseña, monto)) {
                                System.out.println("Abono exitoso");
                                control = false;
                            } else {
                                System.out.println("Fallo en la transacción. Intentar nuevamente. ");
                            }
                        } catch (Exception e) {
                            System.out.println("Cantidad inválida. Intentar nuevamente. ");
                        }
                    }
                    break;
                case 2:
                    monto = 0;
                    contraseña = "";
                    control = true;
                    while (control) {
                        System.out.println("CANTIDAD A RETIRAR: ");
                        try {
                            monto = Integer.parseInt(s.nextLine());
                            System.out.println("CONTRASEÑA: ");
                            contraseña = s.nextLine();
                            if (monto > -1 && instancia.withdrawal(numerodecuenta, contraseña, monto)) {
                                System.out.println("Retiro exitoso");
                                control = false;
                            } else {
                                System.out.println("Fallo en la transacción. Intentar nuevamente. ");
                            }
                        } catch (Exception e) {
                            System.out.println("Cantidad inválida. Intentar nuevamente. ");
                        }
                    }
                    break;
                case 3:
                    control = true;
                    contraseña = "";
                    while (control) {
                        System.out.println("CONTRASEÑA: ");
                        contraseña = s.nextLine();
                        int current = instancia.getSaldo(numerodecuenta, contraseña);
                        if (current > -1) {
                            System.out.println("SALDO ACTUAL: " + current);
                            control = false;
                        } else {
                            System.out.println("Fallo en la transacción. Intentar nuevamente. ");
                        }
                    }
                    break;
                case 4:
                    String destino = "";
                    monto = 0;
                    contraseña = "";
                    control = true;
                    while (control) {
                        System.out.println("CUENTA DESTINATARIA: ");
                        destino = s.nextLine();
                        System.out.println("CANTIDAD A RETIRAR: ");
                        try {
                            monto = Integer.parseInt(s.nextLine());
                            System.out.println("CONTRASEÑA: ");
                            contraseña = s.nextLine();
                            if (monto > -1 && instancia.transaction(numerodecuenta, contraseña, destino, monto)) {
                                System.out.println("Retiro exitoso");
                                control = false;
                            } else {
                                System.out.println("Fallo en la transacción. Intentar nuevamente. ");
                            }
                        } catch (Exception e) {
                            System.out.println("Cantidad inválida. Intentar nuevamente. ");
                        }
                    }
                    break;
                default:
                    instancia.cerrarConexion();
                    s.close();
                    loged = false;
                    break;
            }
        }
    }
}