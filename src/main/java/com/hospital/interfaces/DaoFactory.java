package com.hospital.interfaces;

import com.hospital.dao.DiagDao;
import com.hospital.dao.MedicineDao;
import com.hospital.dao.OperationDao;
import com.hospital.dao.ProcedureDao;

import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Rostislav Stakhov
 */


/** Фабрика объектов для работы с базой данных */
public interface DaoFactory
{
//
    /** Возвращает подключение к базе данных */
    public Connection getConnection() throws SQLException;
//
    /** Возвращает объект для управления персистентным состоянием объекта Employee */
    public IPatientDao getPatientDao() throws Exception;

    public IAppointmentDao getAppointmentDao() throws Exception;

    public DiagDao getDiagDao() throws Exception;

    public OperationDao getOperationDao() throws Exception;

    public MedicineDao getMedicineDao() throws Exception;

    public ProcedureDao getProcedureDao() throws Exception;

}/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


