package com.estudo.agenda.shared.VOs;

public class CPF {
    private String cpf;

    public CPF(String cpf) {
        if (isValido(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private Boolean isValido(String cpf) {

        if (cpf == null) return false;

        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) return false;

        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : 11 - resto;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : 11 - resto;

            int digito1CPF = Character.getNumericValue(cpf.charAt(9));
            int digito2CPF = Character.getNumericValue(cpf.charAt(10));

            return digito1 == digito1CPF && digito2 == digito2CPF;
        } catch (NumberFormatException e) {
            return false;
        }        
    }

    public String cpfSemFormatacao() {
        return this.cpf.replaceAll("\\D", "");
    }

    public String cpfComFormatacao() {
        if (this.cpf.length() == 11) {
            return this.cpf.substring(0, 3) + "." + this.cpf.substring(3, 6) + "." + this.cpf.substring(6, 9) + "-" + this.cpf.substring(9);
        } else {
            return this.cpf;
        }
    }
}
