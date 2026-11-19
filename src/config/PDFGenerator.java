package config;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import model.Despesa;
import model.ItemDespesa;

public class PDFGenerator {

    private static final DateTimeFormatter FMT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FMT_MOEDA = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

   
    public static void gerarRelatorioDespesas(List<Despesa> lista, String caminhoArquivo) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));

        document.open();

        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font fontHeader = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font fontCorpo = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

        Paragraph titulo = new Paragraph("Relatório de Despesas Públicas", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1, 3.5f, 2, 1.5f, 3.5f, 2});

        String[] colunas = {"ID", "Político/Partido", "Valor Total", "Qtd. Itens", "Descrição", "Data"};

        for (String col : colunas) {
            PdfPCell cell = new PdfPCell(new Phrase(col, fontHeader));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPadding(5);
            table.addCell(cell);
        }

        for (Despesa d : lista) {
            String politicoStr = (d.getPolitico() != null) ? d.getPolitico().toString() : "";
            String valorStr = (d.getValor() != null) ? FMT_MOEDA.format(d.getValor()) : "R$ 0,00";
            String qtdItensStr = (d.getItens() != null) ? String.valueOf(d.getItens().size()) : "0";
            String dataStr = (d.getDataRegistro() != null) ? d.getDataRegistro().format(FMT_DATA) : "";

            table.addCell(new Phrase(String.valueOf(d.getId()), fontCorpo));
            table.addCell(new Phrase(politicoStr, fontCorpo));
            table.addCell(new Phrase(valorStr, fontCorpo));
            table.addCell(new Phrase(qtdItensStr, fontCorpo));
            table.addCell(new Phrase(d.getDescricao() != null ? d.getDescricao() : "", fontCorpo));
            table.addCell(new Phrase(dataStr, fontCorpo));
        }

        document.add(table);
        document.close();
    }

    public static void gerarComprovanteDespesa(Despesa d, String caminhoArquivo) throws Exception {
        Document document = new Document(PageSize.A4);
        FileOutputStream fos = new FileOutputStream(caminhoArquivo);

        try {
            PdfWriter.getInstance(document, fos);
            document.open();

            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font fontLabel = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Font fontTexto = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            Font fontHeaderTabela = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
            Font fontCorpoTabela = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

          
            Paragraph titulo = new Paragraph("COMPROVANTE DE PRESTAÇÃO DE CONTAS / DESPESA PÚBLICA", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

          
            Paragraph dadosOperacao = new Paragraph();
            dadosOperacao.setSpacingAfter(15);

            dadosOperacao.add(new Phrase("Registro da Despesa Nº: ", fontLabel));
            dadosOperacao.add(new Phrase(String.valueOf(d.getId()) + "\n", fontTexto));

            String dataStr = (d.getDataRegistro() != null) ? d.getDataRegistro().format(FMT_DATA) : "-";
            dadosOperacao.add(new Phrase("Data de Registro: ", fontLabel));
            dadosOperacao.add(new Phrase(dataStr + "\n\n", fontTexto));

            if (d.getPolitico() != null) {
                String nomePol = (d.getPolitico().getNome() != null) ? d.getPolitico().getNome() : "-";
                String partidoPol = (d.getPolitico().getPartido() != null) ? d.getPolitico().getPartido() : "-";
                String cargoPol = (d.getPolitico().getCargo() != null) ? d.getPolitico().getCargo().toString() : "-";
                String cpfPol = (d.getPolitico().getCpf() != null) ? d.getPolitico().getCpf() : "-";

                dadosOperacao.add(new Phrase("Parlamentar / Político: ", fontLabel));
                dadosOperacao.add(new Phrase(nomePol + "\n", fontTexto));

                dadosOperacao.add(new Phrase("Partido / Cargo: ", fontLabel));
                dadosOperacao.add(new Phrase(partidoPol + " - " + cargoPol + "\n", fontTexto));

                dadosOperacao.add(new Phrase("CPF: ", fontLabel));
                dadosOperacao.add(new Phrase(cpfPol + "\n\n", fontTexto));
            }

            dadosOperacao.add(new Phrase("Descrição / Observação: ", fontLabel));
            dadosOperacao.add(new Phrase(d.getDescricao() != null && !d.getDescricao().isEmpty() ? d.getDescricao() : "Sem observações", fontTexto));

            document.add(dadosOperacao);

            
            Paragraph subItens = new Paragraph("Itens / Serviços Contratados", fontSubtitulo);
            subItens.setSpacingBefore(10);
            subItens.setSpacingAfter(10);
            document.add(subItens);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3.5f, 2.5f, 1, 2, 2});

            String[] colunas = {"Serviço / Recurso", "Categoria", "Qtd", "Valor Unit.", "Subtotal"};
            for (String col : colunas) {
                PdfPCell cell = new PdfPCell(new Phrase(col, fontHeaderTabela));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }

            if (d.getItens() != null && !d.getItens().isEmpty()) {
                for (ItemDespesa item : d.getItens()) {
                    String descServico = (item.getServicoRecurso() != null && item.getServicoRecurso().getDescricao() != null)
                            ? item.getServicoRecurso().getDescricao() : "-";
                    String catServico = (item.getServicoRecurso() != null && item.getServicoRecurso().getCategoria() != null)
                            ? item.getServicoRecurso().getCategoria().toString() : "-";

                    String valorUnitStr = (item.getValorUnitario() != null) ? FMT_MOEDA.format(item.getValorUnitario()) : "R$ 0,00";
                    String subtotalStr = (item.getSubtotal() != null) ? FMT_MOEDA.format(item.getSubtotal()) : "R$ 0,00";

                    table.addCell(new Phrase(descServico, fontCorpoTabela));
                    table.addCell(new Phrase(catServico, fontCorpoTabela));

                    PdfPCell cellQtd = new PdfPCell(new Phrase(String.valueOf(item.getQuantidade()), fontCorpoTabela));
                    cellQtd.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellQtd);

                    table.addCell(new Phrase(valorUnitStr, fontCorpoTabela));
                    table.addCell(new Phrase(subtotalStr, fontCorpoTabela));
                }
            } else {
                PdfPCell cellVazia = new PdfPCell(new Phrase("Nenhum item registrado nesta despesa.", fontCorpoTabela));
                cellVazia.setColspan(5);
                cellVazia.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellVazia);
            }

            document.add(table);

            Paragraph totalParagrafo = new Paragraph(
                    "\nVALOR TOTAL DA DESPESA: " + (d.getValor() != null ? FMT_MOEDA.format(d.getValor()) : "R$ 0,00"),
                    fontSubtitulo
            );
            totalParagrafo.setAlignment(Element.ALIGN_RIGHT);
            totalParagrafo.setSpacingBefore(15);
            document.add(totalParagrafo);

        } finally {
            if (document.isOpen()) {
                document.close();
            }
            fos.close();
        }
    }

}
