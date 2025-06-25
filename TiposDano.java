public enum TiposDano {
    NORMAL(1.0),
    CRITICO(2.0);

    private final double multiplicador;

    TiposDano(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double calcularDano(double danoBase) {
        return danoBase * multiplicador;
    }
    public String getDanoFormatado() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}