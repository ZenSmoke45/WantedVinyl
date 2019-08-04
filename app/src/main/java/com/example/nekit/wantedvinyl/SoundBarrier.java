package com.example.nekit.wantedvinyl;

import com.google.android.gms.maps.model.LatLng;

public class SoundBarrier extends Place{

    public SoundBarrier() {
        super("Sound Barrier", "http://sound-barrier.ru/Ru/Catalog/Search.aspx?ArtistOrTitle=%query%&Currency=rub&PageSize=100&ViewStyle=Table", new LatLng(55.68708996, 37.54220366));
    }

    @Override
    public QueryResponse checkIfInStock(String item) {
        String str = Utils.sendGet(this.getURL().replace("%query%", item.toUpperCase()));

        if (str==null) return QueryResponse.UNSUCCESSFUL;

        try {
            str = str.substring(str.indexOf("<table class=\"DataArea\" cellspacing=\"0\" border=\"1\" style=\"width:100%\">"));
        } catch(StringIndexOutOfBoundsException e) {
            return QueryResponse.UNSUCCESSFUL;
        }
        str = str.substring(0, str.indexOf("</table>"));

        String current, info, result = "";
        while(true) {
            try {

                current = Utils.substring(str, "<tr>", "</tr>");
                str = str.replace(current, "");

                for (int i=0;i<9;i++) {
                    try {

                        try {
                            info = Utils.substring(current, "<td class=\"DataValue TableDataValue\"  style=\"text-align: right\">", "</td>");
                            result += Utils.stripArgs(info)+"р\n";
                        } catch(StringIndexOutOfBoundsException e) {}

                        info = Utils.substring(current, "<td class=\"DataValue TableDataValue\">", "</td>");
                        current = current.replace(info, "");

                        switch(i) {
                            case 1:
                                result += "№"+Utils.stripArgs(info)+" ";
                                break;
                            case 3:
                                result += Utils.stripArgs(info)+" - ";
                                break;
                            case 4:
                                result += Utils.stripArgs(info)+" ";
                                break;
                            default:
                                break;
                        }

                    } catch(StringIndexOutOfBoundsException e) {
                        continue;
                    }
                }

            } catch(StringIndexOutOfBoundsException e) {
                break;
            }
        }

        return new QueryResponse(result);
    }
}
