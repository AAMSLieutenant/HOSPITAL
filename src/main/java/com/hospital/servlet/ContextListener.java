package com.hospital.servlet;


import com.hospital.dao.*;


import com.hospital.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
Эта сущность реализует область видимости всего приложения (максимум)
Здесь лежат данные для всего приложения (видимые для всех сервлетов)
Это необходимо сделать до загрузки всех сервлетов.
1. Реализовать интерфейс ServletContextListener
2. Добавить аннтоацию @WebListener
 */

/**
 * @author Rostislav Stakhov
 * Listener for the whole project resourses
 */
@WebListener
public class ContextListener implements ServletContextListener {


    private static final Logger logger= LoggerFactory.getLogger(ContextListener.class);
    private Map<Integer, Patient> patientsDb;//Коллекция пациентов
    private OracleDaoFactory factory;//Фабрика подключений
    private AtomicReference<PatientDao> patientDao;//ДАО для пациентов
    private AtomicReference<AppointmentDao> appointmentDao;//ДАО для приемов
    private AtomicReference<DiagDao> diagDao;//ДАО для диагнозов
    private AtomicReference<UuserDao> userDao;
    private AtomicReference<OperationDao> operationDao;
    private AtomicReference<MedicineDao> medicineDao;
    private AtomicReference<ProcedureDao> procedureDao;

    //Базовый метод, применяется тогда, когда приложение запускается
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("ContextListener initialized");
        Locale.setDefault(Locale.US);
        final ServletContext servletContext = servletContextEvent.getServletContext();





        patientDao = new AtomicReference<>();
        appointmentDao=new AtomicReference<>();
        diagDao=new AtomicReference<>();
        operationDao=new AtomicReference<>();
        medicineDao=new AtomicReference<>();
        procedureDao=new AtomicReference<>();
        this.userDao=new AtomicReference<>();
        this.patientsDb=new ConcurrentHashMap<>();
        this.factory=new OracleDaoFactory();
        try {
            this.appointmentDao.set(factory.getAppointmentDao());
            this.diagDao.set(factory.getDiagDao());
            this.patientDao.set(factory.getPatientDao());
            this.userDao.set(factory.getUuserDao());
            this.operationDao.set(factory.getOperationDao());
            this.medicineDao.set(factory.getMedicineDao());
            this.procedureDao.set(factory.getProcedureDao());
            List<Patient> pats=patientDao.get().getAll();

            for(int i=0;i<pats.size();i++){
                this.patientsDb.put(pats.get(i).getpCardId(),pats.get(i));
            }
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

        servletContext.setAttribute("factory", factory);
        servletContext.setAttribute("patientsDb", patientsDb);
        servletContext.setAttribute("patientDao", patientDao);
        servletContext.setAttribute("appointmentDao", appointmentDao);
        servletContext.setAttribute("diagDao", diagDao);
        servletContext.setAttribute("userDao", userDao);
        servletContext.setAttribute("operationDao", operationDao);
        servletContext.setAttribute("medicineDao", medicineDao);
        servletContext.setAttribute("procedureDao", procedureDao);
    }


    /*
    Когда приложение завершается
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Close recourse.
        patientsDb=null;
    }
}