package br.ufsc.ine5605;

import java.util.ArrayList;

public class ControladorCargo {
	private ArrayList<Cargo> listaCargos;
	private TelaCargo tela;
	private static ControladorCargo instancia;
	
	private ControladorCargo() {
		tela = new TelaCargo();
        listaCargos = new ArrayList<>();
    }
    public static ControladorCargo getInstance(){
    	if(instancia == null){
    		instancia = new ControladorCargo();
    	}
    	return instancia;
    }
    
    public ArrayList<Cargo> getListaCargos() {
    	return listaCargos;
    }
    
    /**
     * Chama a tela que mostra o menu principal (relacionado a Cargos)
     */
    public void mostraMenu(){
    	int opcao = -1;
    	do {
    		opcao = tela.mostraMenuPrincipal();
    		switch (opcao){
    		case 0: 
    			break;
    		case 1: 
    			tela.listarCargos(listaCargos);
    			break;
    		case 2:
    			incluirCargo();
    			break;
    		case 3:
  				excluirCargo();
     			break;
    		case 4:
    			alterarCargo();
    			break;
    		case 5:
    			alterarStatusGerencial();
    			break;
    		case 6:
    			alterarStatusAcesso();
    			break;
    		case 7:
    			alterarHorarios();
    			break;
    		}
    	} while (opcao != 0);
    }
	
   // precisa incluir o codigo do cargo que tera os horarios alterados;
    public void alterarHorarios(){	// arrumar para tratar a excecao no controlador de horario
	   try {
		   ControladorHorario.getInstance().iniciaCadastro();
	   } catch (Exception e) {
		   System.out.println(e.getMessage());
	   }
	}
public void alterarDescricao(){
	   DadosAlteraDescricao dados = tela.alterarDescricao();
	   Cargo c = findCargoByCodigo(dados.codigo);
		if (c != null){
			c.setNome(dados.nome);
			tela.mostraMensagem("Nome alterado com sucesso para \"" + dados.nome + "\"");
			} else {
				tela.mostraMensagem("Nao existe cargo com este codigo (" + dados.codigo + ")");
			}
		}
   
	public void alterarStatusGerencial(){
		DadosAlteraStatus dados = tela.alterarStatus("gerencial");
		Cargo c = findCargoByCodigo(dados.codigo);
		if (c != null){
			c.setEhGerencial(dados.status);
			if (c.ehGerencial()){
				c.getHorariosPermitidos().clear(); //gerentes acessam a qualquer momento
			} else {
				tela.mostraMensagem("Nao esqueca de atualizar/definir os horarios de acesso");
			}
			tela.mostraMensagem("Status definido com sucesso.");
		} else {
			tela.mostraMensagem("Nao existe cargo com este codigo (" + dados.codigo + ")");
		}
}
   
   public void alterarStatusAcesso(){
	   DadosAlteraStatus dados = tela.alterarStatus("acesso");
	   Cargo c = findCargoByCodigo(dados.codigo);
		if (c != null){
			if (!c.ehGerencial()) {
				c.setPossuiAcesso(dados.status);
				if (c.getPossuiAcesso()){
					tela.mostraMensagem("Nao esqueca de atualizar/definir os horarios de acesso");
				} else {
					c.getHorariosPermitidos().clear();
				}
				tela.mostraMensagem("Status definido com sucesso");
				
			}
			} else {
				tela.mostraMensagem("Nao existe cargo com este codigo (" + dados.codigo + ")");
			}
		}
    
    /**
    * Chama a tela para incluir cargo; apos tentativa de inclusao, solicita impressao de mensagem confirmando ou negando a inclusao
    * 
    * @return boolean "true" se cadastrado com sucesso; caso contrario, "false"
    */
    public boolean incluirCargo(){
    	boolean inclusaoOK = false;
		DadosCadastroCargo cargo = tela.incluirCargo();
		if (findCargoByCodigo(cargo.codigo) == null){
			listaCargos.add(new Cargo (cargo.codigo, cargo.nome, cargo.ehGerencial, cargo.possuiAcesso, cargo.horariosPermitidos));
			tela.mostraMensagem("Cargo cadastrado com sucesso");
			inclusaoOK = true;
		} else {
			tela.mostraMensagem("Este cargo ja esta cadastrado");
			inclusaoOK = false;
		}
		return inclusaoOK;
	}
	
	
	/**
	 * 
	 * @param codigo Codigo numerico do cargo a ser excluido
	 * @return boolean indicando sucesso (true) ou fracasso (false)
	 * @throws Exception Codigo Inexistente, impossivel excluir
	 */
	public boolean excluirCargo() {
		int codigo = tela.excluirCargo();
		Cargo c = findCargoByCodigo(codigo);
		boolean remocaoOK = false;
		if (c != null){
			if (ControladorFuncionario.getInstance().listarFuncionariosPorCargo(codigo).isEmpty()){
				listaCargos.remove(c);
				tela.mostraMensagem("Cargo excluido com sucesso");
				remocaoOK = true;
			} else {
				tela.mostraMensagem("Cargo nao pode ser excluido, possui funcionarios associados a ele");
				remocaoOK = false;
			}
		} else {
			tela.mostraMensagem("Codigo Inexistente, impossivel excluir");
		}
		return remocaoOK;
	}
	
//	/** 
//	 * 
//	 * @return listaCargos, ArrayList com os cargos cadastrados
//	 */
//	public ArrayList<Cargo> listarCargos() {
//		return listaCargos; // retorna para imprimir na tela
//	}
	
	
	public Cargo findCargoByCodigo(int codigo){
		Cargo cargo = null;
		for (Cargo c: listaCargos){
			if (c.getCodigo() == codigo){
				cargo = c;
			}
		}
		return cargo;
	}

    
    // "Voltar", "Alterar Descricao", "Alterar status gerencial", "Alterar status de acesso", "Alterar Horarios de Acesso"    
    private void alterarCargo() {
        int opcao = tela.mostraMenuEditar();
        switch (opcao){
            case 0:
                break;
            case 1:
                alterarDescricao();
                break;
            case 2:
                alterarStatusGerencial();
                break;
            case 3:
                alterarStatusAcesso();
                break;
            case 4:
                alterarHorarios();
                break;
        }
    }

}
