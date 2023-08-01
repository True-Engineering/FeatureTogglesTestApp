package ru.trueengineering.testapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.trueengineering.feature.flag.starter.provider.FeatureFlagStateProvider;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/feature-flag/store/features")
@AllArgsConstructor
public class Controller {

    private final FeatureFlagStateProvider provider;

    @GetMapping("/check/{flag}")
    public ResponseEntity<String> check(@PathVariable("flag") String flag) {
        return ResponseEntity.ok(String.valueOf(provider.check(flag)));
    }

    @GetMapping("/getByTag/{tag}")
    public ResponseEntity<Map<String, Boolean>> getByTag(@PathVariable("tag") String tag) {
        return ResponseEntity.ok(provider.getByTag(tag));
    }
}
