package gg.techquest.util.database.callback;

import org.bson.Document;

public interface DocumentCallback {
    void call(Document document, boolean found);
}