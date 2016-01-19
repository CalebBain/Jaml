package QtComponents;

import com.trolltech.qt.core.QObject;
import com.trolltech.qt.gui.QWidget;
import org.w3c.dom.Node;

/**
 * Created by Caleb Bain on 1/9/2016.
 */
public interface Component {

    public String setStyle();

    public String Name();

    public String Class();

    public String Component();

    public QObject Widgit();

    public void SetStylesheet(String sheet);

    public void addChild(Component child, Node node);

}
