
package android.databinding;
import com.example.lenovo.doubanapplication.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.example.lenovo.doubanapplication.R.layout.item_header:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/item_header_0".equals(tag)) {
                            return new com.example.lenovo.doubanapplication.databinding.ItemHeaderBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for item_header is invalid. Received: " + tag);
                }
                case com.example.lenovo.doubanapplication.R.layout.fragment_music:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/fragment_music_0".equals(tag)) {
                            return new com.example.lenovo.doubanapplication.databinding.FragmentMusicBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for fragment_music is invalid. Received: " + tag);
                }
                case com.example.lenovo.doubanapplication.R.layout.activity_list:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_list_0".equals(tag)) {
                            return new com.example.lenovo.doubanapplication.databinding.ActivityListBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_list is invalid. Received: " + tag);
                }
                case com.example.lenovo.doubanapplication.R.layout.fragment_movie:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/fragment_movie_0".equals(tag)) {
                            return new com.example.lenovo.doubanapplication.databinding.FragmentMovieBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for fragment_movie is invalid. Received: " + tag);
                }
                case com.example.lenovo.doubanapplication.R.layout.activity_detail:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_detail_0".equals(tag)) {
                            return new com.example.lenovo.doubanapplication.databinding.ActivityDetailBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_detail is invalid. Received: " + tag);
                }
                case com.example.lenovo.doubanapplication.R.layout.item_search:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/item_search_0".equals(tag)) {
                            return new com.example.lenovo.doubanapplication.databinding.ItemSearchBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for item_search is invalid. Received: " + tag);
                }
                case com.example.lenovo.doubanapplication.R.layout.activity_tab:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_tab_0".equals(tag)) {
                            return new com.example.lenovo.doubanapplication.databinding.ActivityTabBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_tab is invalid. Received: " + tag);
                }
                case com.example.lenovo.doubanapplication.R.layout.fragment_book:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/fragment_book_0".equals(tag)) {
                            return new com.example.lenovo.doubanapplication.databinding.FragmentBookBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for fragment_book is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case -1545131899: {
                if(tag.equals("layout/item_header_0")) {
                    return com.example.lenovo.doubanapplication.R.layout.item_header;
                }
                break;
            }
            case 83918316: {
                if(tag.equals("layout/fragment_music_0")) {
                    return com.example.lenovo.doubanapplication.R.layout.fragment_music;
                }
                break;
            }
            case 402815770: {
                if(tag.equals("layout/activity_list_0")) {
                    return com.example.lenovo.doubanapplication.R.layout.activity_list;
                }
                break;
            }
            case -85084105: {
                if(tag.equals("layout/fragment_movie_0")) {
                    return com.example.lenovo.doubanapplication.R.layout.fragment_movie;
                }
                break;
            }
            case 257710925: {
                if(tag.equals("layout/activity_detail_0")) {
                    return com.example.lenovo.doubanapplication.R.layout.activity_detail;
                }
                break;
            }
            case 458772704: {
                if(tag.equals("layout/item_search_0")) {
                    return com.example.lenovo.doubanapplication.R.layout.item_search;
                }
                break;
            }
            case -1642441125: {
                if(tag.equals("layout/activity_tab_0")) {
                    return com.example.lenovo.doubanapplication.R.layout.activity_tab;
                }
                break;
            }
            case -1287703484: {
                if(tag.equals("layout/fragment_book_0")) {
                    return com.example.lenovo.doubanapplication.R.layout.fragment_book;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"};
    }
}