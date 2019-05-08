package com.empresa.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.empresa.dao.PersonaDao;
import com.empresa.entities.Beneficiario;
import com.empresa.entities.Cliente;
import com.empresa.entities.Direccion;
import com.empresa.entities.Persona;
import com.empresa.entities.PersonaGenerica;
import com.empresa.entities.Referencia;
import com.empresa.entities.Telefono;

@ManagedBean
@ViewScoped
public class ResgistroMb {
	private Persona persona;
	private List<Cliente> cliList;
	private Cliente cliente;
	private Direccion direccion;
	private List<Direccion> listDirCliente;
	private List<Telefono> telListCliente;
	private List<PersonaGenerica> referencias;	
	private List<PersonaGenerica> beneficiarios;
	private List<Referencia> refListRefCliente;
	private List<Beneficiario> refListBenCliente;
	private PersonaDao perDao ;
	
	

	@PostConstruct
	public void init() {
		 persona = new Persona();
		 cliList = new ArrayList<Cliente>(); 
		 cliente = new Cliente();
		 direccion = new Direccion();
		 listDirCliente= new ArrayList<Direccion>();
		 telListCliente= new ArrayList<Telefono>();
		 referencias= new ArrayList<PersonaGenerica>();
		 beneficiarios= new ArrayList<PersonaGenerica>();
		 refListRefCliente= new ArrayList<Referencia>();
		 refListBenCliente= new ArrayList<Beneficiario>();
		 perDao= new PersonaDao();
		 
	}
	

	public void insertCliente() {
		cliente.setPersona(persona);
		cliList.add(cliente);
		persona.setClientes(cliList);
		for(Direccion dir:listDirCliente) {
			dir.setPersona(persona);
		}
		persona.setDireccions(listDirCliente);
		for(Telefono tel:telListCliente) {
			tel.setPersona(persona);
		}
		persona.setTelefonos(telListCliente);
		for(PersonaGenerica per: beneficiarios) {
			Beneficiario ben = new Beneficiario();
			ben.setCliente(cliente);
			ben.setEdad(per.getEdad());
			ben.setParentesco(per.getParentesco());
			Persona perBen = new Persona();
			perBen.setNombres(per.getNombres());
			perBen.setApellidos(per.getApellidos());
			perBen.setNumeroDocumento(per.getNumeroDocumento());
			Persona perRespuesta= perDao.insertPersona(perBen);
			ben.setPersona(perRespuesta);
			ben.setPorcentaje(per. getPorcentaje());
			refListBenCliente.add(ben);
		}
		persona.setBeneficiarios(refListBenCliente);
		for(PersonaGenerica perGen: referencias) {
			Referencia ref = new Referencia();
			ref.setCliente(cliente);
			Persona perBen=new Persona();
			perBen.setNombres(perGen.getNombres());
			perBen.setApellidos(perGen.getApellidos());
			perBen.setNumeroDocumento(perGen.getNumeroDocumento());
			Persona personaGenRespuesta= perDao.insertPersona(perBen);
			ref.setPersona(personaGenRespuesta);
			ref.setTiempoconocerse(perGen.getTiempoconocerse());
			refListRefCliente.add(ref);
		}
		persona.setReferencias(refListRefCliente);
		Persona clienteGuardao= perDao.insertPersona(persona);
		FacesMessage msg = new FacesMessage("Persona Guardada"+clienteGuardao.getIdPersona());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public String iraCuentas() {
		return "cuentas.xhtml";
	}

	public void addDireccion() {
		listDirCliente.add(new Direccion());
	}
	
	public void addTelefonoCliente() {
		telListCliente.add(new Telefono());
	}
	public void addReferencia() {
		referencias.add(new PersonaGenerica());	
	}
	public void addBeneficiario() {
		beneficiarios.add(new PersonaGenerica());	
	}
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Cliente> getCliList() {
		return cliList;
	}

	public void setCliList(List<Cliente> cliList) {
		this.cliList = cliList;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public List<Direccion> getListDirCliente() {
		return listDirCliente;
	}

	public void setListDirCliente(List<Direccion> listDirCliente) {
		this.listDirCliente = listDirCliente;
	}

	public List<Telefono> getTelListCliente() {
		return telListCliente;
	}

	public void setTelListCliente(List<Telefono> telListCliente) {
		this.telListCliente = telListCliente;
	}

	public List<Referencia> getRefListRefCliente() {
		return refListRefCliente;
	}

	public void setRefListRefCliente(List<Referencia> refListRefCliente) {
		this.refListRefCliente = refListRefCliente;
	}

	public List<Beneficiario> getRefListBenCliente() {
		return refListBenCliente;
	}

	public void setRefListBenCliente(List<Beneficiario> refListBenCliente) {
		this.refListBenCliente = refListBenCliente;
	}

	public PersonaDao getPerDao() {
		return perDao;
	}

	public void setPerDao(PersonaDao perDao) {
		this.perDao = perDao;
	}
	
	public List<PersonaGenerica> getReferencias() {
		return referencias;
	}

	public void setReferencias(List<PersonaGenerica> referencias) {
		this.referencias = referencias;
	}

	public List<PersonaGenerica> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(List<PersonaGenerica> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	
	
}
