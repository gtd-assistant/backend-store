package io.alvarogarcia7.petprojects.gtdassistant.backend.persistence;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class DataSourceBuilder {

    private List<String> scripts;

    public DataSourceBuilder() {
        this.scripts = new ArrayList<>();
    }

    public static DataSourceBuilder aNewH2() {
        return new DataSourceBuilder();
    }

    public DataSourceBuilder loadProductionMigrations() {
        listDbMigrationsAt(Paths.get("src/main/resources")).forEach(scripts::add);
        return this;
    }

    public DataSourceBuilder loadTestMigrations() {
        listDbMigrationsAt(Paths.get("src/test/resources")).forEach(scripts::add);
        return this;
    }

    public DataSource build() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScripts(scripts.toArray(new String[0]))
                .build();
    }

    private Stream<String> listDbMigrationsAt(Path path1) {
        path1 = path1.resolve("db/migration");
        return listFilesAt(path1)
                .stream()
                .map(path -> path.subpath(3, path.getNameCount()))
                .map(Path::toString);
    }

    private List<Path> listFilesAt(Path path) {
        try {
            return Files.list(path).collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
