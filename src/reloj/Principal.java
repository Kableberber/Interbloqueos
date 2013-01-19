/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Principal extends javax.swing.JFrame implements Runnable { //correr la interface de el reloj

    String fechaActual, hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    static String alarma = "05:40:00 PM";

    public Principal() {


        initComponents();


        Calendar cal = Calendar.getInstance();
        String fecha = cal.get(cal.DATE) + "/" + cal.get(cal.MONTH) + "/" + cal.get(cal.YEAR);//parametros de la hora
        this.jLabel1.setText(fecha);
        setTitle("Kable");
        h1 = new Thread(this);
        h1.start();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void run() {//correr todas las variables y los hilos de reloj.java
        boolean resultado = false;
        String horaString;
        Thread ct = Thread.currentThread();
        while (ct == h1) {//ciclo para refrescar la hora
            calcula();
            horaString = hora + ":" + minutos + ":" + segundos + " " + ampm;//asiganacion de una sola variable a horas 

            if (resultado == false && segundos.equals("00")) {//comparacion de estado de la alarma
                
                resultado = this.verificarAlarma(horaString);
            }

            lbHora.setText(horaString);
            jLabel2.setText(alarma);
             System.out.println(alarma); 
            if (resultado) {
                System.out.println("parate weee");
                     
                
            }
            try {
                Thread.sleep(1000);//hilo que refresca la hora 
            } catch (InterruptedException e) {
            }
        }
    }

    public void calcula() {//contador que calcula el tipo y formato de la hora
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbHora = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbHora.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHora.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 48)); // NOI18N
        jLabel1.setText("00/00/00");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHora, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbHora;
    // End of variables declaration//GEN-END:variables

    private boolean verificarAlarma(String horaString) {
        boolean resultado = false;
        
        if (horaString.equals(alarma)) {
        System.out.println("Que pedo Puto");    
            resultado = true;
        }

        return resultado;//regresa resultado del estado de la alarma
    }
}
