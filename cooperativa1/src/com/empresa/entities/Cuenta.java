package com.empresa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c"),
	@NamedQuery(name="cuentas.byCliente", query="SELECT c FROM Cuenta c WHERE c.cliente.idCliente =:idCliente")  
})

public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cuenta")
	private int idCuenta;

	private String numeroCuenta;

	private float saldo;

	private float totalAbonos;

	private float totalCargos;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Cuentatipo
	@ManyToOne
	@JoinColumn(name="id_cuentatipo")
	private Cuentatipo cuentatipo;

	//bi-directional many-to-one association to Transaccione
	@OneToMany(mappedBy="cuenta", cascade =CascadeType.PERSIST )
	private List<Transaccione> transacciones;

	public Cuenta() {
	}

	public int getIdCuenta() {
		return this.idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public float getSaldo() {
		return this.saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public float getTotalAbonos() {
		return this.totalAbonos;
	}

	public void setTotalAbonos(float totalAbonos) {
		this.totalAbonos = totalAbonos;
	}

	public float getTotalCargos() {
		return this.totalCargos;
	}

	public void setTotalCargos(float totalCargos) {
		this.totalCargos = totalCargos;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cuentatipo getCuentatipo() {
		return this.cuentatipo;
	}

	public void setCuentatipo(Cuentatipo cuentatipo) {
		this.cuentatipo = cuentatipo;
	}

	public List<Transaccione> getTransacciones() {
		return this.transacciones;
	}

	public void setTransacciones(List<Transaccione> transacciones) {
		this.transacciones = transacciones;
	}

	public Transaccione addTransaccione(Transaccione transaccione) {
		getTransacciones().add(transaccione);
		transaccione.setCuenta(this);

		return transaccione;
	}

	public Transaccione removeTransaccione(Transaccione transaccione) {
		getTransacciones().remove(transaccione);
		transaccione.setCuenta(null);

		return transaccione;
	}

}