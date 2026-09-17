package com.example.passengerdriverapp.data.api;

import java.util.Arrays;
import java.util.List;

public class JeepneyData {
    private JeepneyData() {
    }

    public static final List<JeepneyRoute> ROUTES = Arrays.asList(
            new JeepneyRoute(
                    "laoag_terminal",
                    "Laoag Terminal",
                    "Laoag",
                    "Main jeepney terminal",
                    "2 min",
                    18.1975,
                    120.5931),
            new JeepneyRoute(
                    "batac_terminal",
                    "Batac Terminal",
                    "Batac",
                    "Main jeepney terminal",
                    "3 min",
                    18.0553,
                    120.5636),
            new JeepneyRoute(
                    "paoay_terminal",
                    "Paoay Terminal",
                    "Paoay",
                    "Main jeepney terminal",
                    "5 min",
                    18.0669,
                    120.5378),
            new JeepneyRoute(
                    "laoag_batac",
                    "Laoag - Batac",
                    "Laoag-Batac",
                    "Coming from Laoag Public Market",
                    "4 min",
                    18.1878,
                    120.5931),
            new JeepneyRoute(
                    "batac_paoay",
                    "Batac - Paoay",
                    "Batac-Paoay",
                    "Coming from Batac City Hall",
                    "7 min",
                    18.0567,
                    120.5641),
            new JeepneyRoute(
                    "paoay_laoag",
                    "Paoay - Laoag",
                    "Paoay-Laoag",
                    "Coming from Paoay Church",
                    "12 min",
                    18.0678,
                    120.5388),
            new JeepneyRoute(
                    "laoag_paoay_express",
                    "Laoag - Paoay Express",
                    "LP Express",
                    "Coming from Robinsons Laoag",
                    "15 min",
                    18.1290,
                    120.5620));
}
