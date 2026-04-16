package be.bstorm.introjakartaee.data;

import be.bstorm.introjakartaee.models.Bike;

import java.util.ArrayList;
import java.util.List;

public class FakeDb {

    public static List<Bike> bikes;

    static {
        bikes = new ArrayList<>();
        bikes.add(new Bike(
                1,
                "Kawasaki",
                "Ninja H2",
                300,
                "https://www.kawasaki.be/content/dam/products/pim/studio/nin/Resource_299076_24MY_Ninja_H2R_GY3_STU.jpg/_jcr_content/renditions/cq5dam.thumbnail.600.600.png"
        ));
        bikes.add(new Bike(
                2,
                "Kawasaki",
                "Ninja ZX-10R",
                210,
                "https://www.kawasaki.be/content/dam/products/pim/studio/Resource_324964_26ZX1003A_40TGN1DRF3CG_A.jpg"
                ));
        bikes.add(new Bike(
                3,
                "Kawasaki",
                "Ninja 400",
                45,
                "https://storage.kawasaki.eu/public/kawasaki.eu/en-EU/model/Ninja-400-GN2-Performance-front_001.png"
        ));
        bikes.add(new Bike(
                4,
                "Kawasaki",
                "Ninja 650",
                68,
                "https://www.kawasaki.be/content/dam/products/pim/studio/s/Resource_320273_26EX650P_S_44TGN1DRF3CG_A.jpg"
        ));
        bikes.add(new Bike(
                5,
                "Kawasaki",
                "Z900RS",
                109,
                "https://www.kawasaki.be/content/dam/products/pim/studio/a/Resource_324750_26ZR902A_A40TRD1DLS3CG_A.jpg/_jcr_content/renditions/cq5dam.web.1280.1280.png"
        ));
        bikes.add(new Bike(
                6,
                "Kawasaki",
                "Z H2",
                236,
                "https://www.kawasaki.be/content/dam/products/pim/studio/r/Resource_320876_26ZR1000L_R_40TGY1DRF3CG_A.jpg"
                ));
        bikes.add(new Bike(
                7,
                "Kawasaki",
                "Ninja 1000 SX",
                143,
                "https://www.kawasaki.be/content/dam/products/pim/resource/Resource_321239_26ZX1100J_40TBU1DRF3CG_A.jpg/_jcr_content/renditions/cq5dam.thumbnail.600.600.png"
        ));
        bikes.add(new Bike(
                8,
                "Kawasaki",
                "Vulcan S",
                61,
                "https://www.kawasaki.be/content/dam/products/pim/studio/m/Resource_320137_26EN650D_M_44TGY1DRF1CG_A.jpg"
                ));
        bikes.add(new Bike(
                9,
                "Kawasaki",
                "Z650",
                68,
                "https://www.kawasaki.be/content/dam/products/pim/studio/Resource_324316_26ER650W_44TGY1DRF3CG_A.jpg"
        ));
        bikes.add(new Bike(
                10,
                "Kawasaki",
                "Ninja ZX-6R",
                130,
                "https://www.kawasaki.be/content/dam/products/pim/studio/Resource_320148_26ZX636J_40TGN1DRF3CG_A.jpg"
        ));
    }
}
