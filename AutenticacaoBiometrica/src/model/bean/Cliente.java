/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author work-
 */
public class Cliente extends Pessoa {
    
    private String terreno;
    private String Status;

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    public Cliente (){
        
    }
    public Cliente (String nome, int id, String terreno, String Status ){
        this.name= nome;
        this.id=id;
        this.terreno=terreno;
        this.Status=Status;
    }
    
}
