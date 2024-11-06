/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author work-
 */
public class Funcionario extends Pessoa {
    private String cargo;
    
    public Funcionario(String nome,int id,String bithday,String cpf,String foto,String cargo){
       this.name=nome;
       this.id=id;
       this.birthday=bithday;
       this.foto=foto;
       this.cpf=cpf;
   
       this.cargo=cargo;
    }
    public Funcionario (){
    
}
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
