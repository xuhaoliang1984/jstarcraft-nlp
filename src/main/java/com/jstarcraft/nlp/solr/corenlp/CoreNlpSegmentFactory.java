package com.jstarcraft.nlp.solr.corenlp;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jstarcraft.nlp.solr.NlpSegmentFactory;
import com.jstarcraft.nlp.tokenization.NlpToken;
import com.jstarcraft.nlp.tokenization.NlpTokenizer;
import com.jstarcraft.nlp.tokenization.corenlp.CoreNlpTokenizer;

import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class CoreNlpSegmentFactory extends NlpSegmentFactory<Annotator> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreNlpSegmentFactory.class);

    public CoreNlpSegmentFactory(Map<String, String> configurations) {
        super(configurations);
    }

    @Override
    protected NlpTokenizer<? extends NlpToken> getNlpTokenizer(Map<String, String> configurations) {
        Annotator annotator = build(configurations);
        CoreNlpTokenizer tokenizer = new CoreNlpTokenizer(annotator);
        return tokenizer;
    }

    @Override
    public Annotator build(Map<String, String> configurations) {
        Properties properties = new Properties();
        for (Entry<String, String> keyValue : configurations.entrySet()) {
            String key = keyValue.getKey();
            String value = keyValue.getValue();
            properties.put(key, value);
        }
        Annotator annotator = new StanfordCoreNLP(properties);
        return annotator;
    }

}
