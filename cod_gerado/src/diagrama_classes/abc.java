package diagrama_classes;

public class abc {
    public static void main(String[] args) throws Exception{

        Treinador t = new Treinador();
        t.setEmail("bata");t.setNome("ta");t.setPassword("password");
        t.setAltura((float) 179.0);t.setIdade(23);t.setGenero(true);t.setPeso(70);
        TreinadorDAO.save(t);

        Exercicio e = new Exercicio();
        byte[] b = new byte[3];
        b[0]=34;b[1]=43;b[2]=123;
        e.setNome("flexoes");e.setCriador_exercicio(t);e.setDescricao("sao fixes");
        e.setDuracao_media(20);e.setMaterial_necessario("maos");e.setConteudo_media(b);
        ExercicioDAO.save(e);

        System.out.println("JÁ ESTÀ");
    }
}
