package com.empresa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.empresa.entities.Cliente;
import com.empresa.entities.Cuenta;
//import com.empresa.entities.Cuenta;
import com.empresa.entities.Persona;
import com.empresa.entities.Transaccione;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class CuentaDao {

	private Persona afiliadoSeleccionado;
	private List<Persona> afiliados;
	private Cuenta cuentaSeleccionada;
	private List<Cuenta> cuentas;
	private List<Transaccione> transacciones;
	
	
	public List<Persona> getClientes(){
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("cooperativa");
		EntityManager em= emf.createEntityManager();
		try {
			afiliados = new ArrayList<Persona>();
			afiliados = em.createNamedQuery("Persona.clientes").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return afiliados;
	}
	
	public List<Cuenta> getCuentas(Cliente cli){
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("cooperativa");
		EntityManager em= emf.createEntityManager();
		try {
			cuentas = new ArrayList<Cuenta>();
			cuentas = em.createNamedQuery("cuentas.byCliente").setParameter("idCliente", cli.getIdCliente()).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cuentas;
	}
}
