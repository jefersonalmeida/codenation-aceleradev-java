package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    List<Time> teams = new ArrayList<>();
    List<Jogador> players = new ArrayList<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

        if (searchTeamForId(id, false) != null) throw new IdentificadorUtilizadoException();

        Time team = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);

        teams.add(team);
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

        if (searchPlayerForId(id, false) != null) throw new IdentificadorUtilizadoException();
        if(nivelHabilidade > 100 || nivelHabilidade < 0) throw new IllegalArgumentException();

        Time team = searchTeamForId(idTime, true);

        Jogador player = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

        team.addJogador(player);
        players.add(player);
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {

        Jogador player = searchPlayerForId(idJogador, true);

        Time team = searchTeamForId(player.getIdTime(), false);

        team.setCapitao(player.getId());
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {

        Time team = searchTeamForId(idTime, true);

        if (team.getCapitao() == null) throw new CapitaoNaoInformadoException();

        return team.getCapitao();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {

        Jogador player = searchPlayerForId(idJogador, true);

        return player.getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {

        Time team = searchTeamForId(idTime, true);

        return team.getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {

        Time team = searchTeamForId(idTime, true);

        return team.getJogadores()
                .stream()
                .sorted(Comparator.comparing(Jogador::getId))
                .map(Jogador::getId)
                .collect(Collectors.toList());
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {

        return searchPlayerForComparison(idTime, Comparator.comparing(Jogador::getNivelHabilidade).reversed());
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {

        return searchPlayerForComparison(idTime, Comparator.comparing(Jogador::getDataNascimento));
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {

        return teams
                .stream()
                .sorted(Comparator.comparing(Time::getId))
                .map(Time::getId)
                .collect(Collectors.toList());
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {

        return searchPlayerForComparison(idTime, Comparator.comparing(Jogador::getSalario).reversed());
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {

        Jogador jogador = searchPlayerForId(idJogador, true);

        return jogador.getSalario();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {

        if (top == null) throw new NullPointerException("top é obrigatório");
        if(players.size() <= 0) return new ArrayList<Long>();
        if(players.size() < top) throw new IllegalArgumentException();

        final Comparator<Jogador> comparator = Comparator
                .comparingInt(Jogador::getNivelHabilidade)
                .reversed()
                .thenComparing(Jogador::getId);

        return players.stream()
                .sorted(comparator)
                .limit(top)
                .map(Jogador::getId)
                .collect(Collectors.toList());
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

        if (timeDaCasa == null || timeDeFora == null)
            throw new NullPointerException("timeDaCasa e/ou timeDeFora não pode ser null");

        Time homeTeam = searchTeamForId(timeDaCasa, true);
        Time guestTeam = searchTeamForId(timeDeFora, true);

        return homeTeam.getCorUniformePrincipal().equals(guestTeam.getCorUniformePrincipal())
                ? guestTeam.getCorUniformeSecundario()
                : guestTeam.getCorUniformePrincipal();
    }

    private Time searchTeamForId(Long id, Boolean isException) {

        if (id == null) throw new NullPointerException("id é obrigatório");

        Time team = this.teams.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);

        if (team == null && isException) throw new TimeNaoEncontradoException();

        return team;
    }

    private Jogador searchPlayerForId(Long id, Boolean isException) {

        if (id == null) throw new NullPointerException("id é obrigatório");

        Jogador player = this.players.stream().filter(jog -> jog.getId().equals(id)).findFirst().orElse(null);

        if (player == null && isException) throw new JogadorNaoEncontradoException();

        return player;
    }

    private Long searchPlayerForComparison(Long idTime, Comparator<Jogador> comparing) {

        Time team = searchTeamForId(idTime, true);

        final Comparator<Jogador> comparator = comparing.thenComparing(Jogador::getId);

        final List<Jogador> sorted = team.getJogadores().stream().sorted(comparator).collect(Collectors.toList());

        return sorted.get(0).getId();
    }

}
