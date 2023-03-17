package src.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionSystem {
    class Trie{
        Trie nodes[] = new Trie[26];
        int c = 0;
    }
    Trie head = new Trie();
    List<List<String>> ans = new ArrayList();

    void insert(String s){
        Trie node = head;
        for(int i=0;i<s.length();i++){
            if(node.nodes[s.charAt(i)-'a']==null){
                node.nodes[s.charAt(i)-'a'] = new Trie();
            }
            node = node.nodes[s.charAt(i)-'a'];
        }
        node.c=1;
    }
    void dfs(Trie node,String gen,List<String> list){
        if(node.c==1){
            list.add(gen);
        }
        if(list.size()==3){
            return;
        }
        int c=0;
        for(int i=0;i<26;i++){
            if(node.nodes[i]!=null){
                c++;
                dfs(node.nodes[i],gen+(char)('a'+i),list);
            }
        }
    }
    void search(String key){
        Trie node = head;
        for(int i=0;i<key.length();i++){
            int c = key.charAt(i)-'a';
            List<String> tl = new ArrayList();
            if(node.nodes[c]!=null){
                node = node.nodes[c];

                dfs(node,key.substring(0,i+1),tl);

            }
            ans.add(tl.size()>3?tl.subList(0,3):tl);
        }
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for(String s : products){
            insert(s);
        }
        search(searchWord);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SearchSuggestionSystem().suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"},"mouse"));
        System.out.println(new SearchSuggestionSystem().suggestedProducts(new String[]{"havana"},"havana"));
        System.out.println(new SearchSuggestionSystem().suggestedProducts(new String[]{"havana"},"tatiana"));
        System.out.println(new SearchSuggestionSystem().suggestedProducts(new String[]{"oqjrtcokaagakchrwrdbrlpnqivwcpzwuxbdkpkhndevouwyrtmbokxolhbvrencthmyplqixnhnokbhtbstmslfbinsypubqjckiqujvmknxuomdwqkfudgiqmpzkvnshrmnoeonzyfaipdcfdwhekrazfkdlfluyvoevahsyhvqjfcizxjhucbpqdjgmqqalqnvfyrtfkyrxlavfbagxkdloetaiedqbrmtzxwwrpyewrcbntsnrdzurzlfokcxolkltouozdobqvtoldjuincumspqpujynxxetbflfnkrscxgrvennmczurjvnoalxcfcrnddfckavedjrvewmawxazviumzpudgolofsxllhgsdrnvjbipcsqsdoosfdkhavhicsfbyavwyxziefmycknhzqujqkbqhacuaebwqpkfnkovxchczohhirczixikuoktkaamchcghynclonujiyzkcghjjjtzckjxmpssfqjirnfvrddvoqvdeteegupzevydcywjsoybsflpgpzkcoztcayffscwxkofwibguysdjtmddgevjhbxrfiqliqyiczdunhosdctrvlagcugpsskpvlsdhcbpdtdwjbhchymlptjrmzmezwljxemgzecptluxbwtgoyvqwrvxkartgrebdegyqdibemnficwpgsywlzczvwujmykkyhkkxhvrtmjxcyidawfxjcagsgutndplixpepddiosflfoujxndtgxsbzarlwacjtydorlrtukbyibaktiphojmrcbwjppnuwwihuerqswjpmyblrdslnwpdnzovpslsrfbzhjywbimwdiqhxmdssjovufgbqqykozfjbylihfwanzjdtgeoimwowocykeskvivwuybdrowolstbrbenioagkgwednhymqisdvjuycjvtrurzqssxrisozysizxeoyelhglnppsjzggjunmyabzptlfktzphlmhvpoqlnrwgxauepumssyecjpwfozhxhvodsasomhtgbasijrpphbijnxcyrajypogighdxacjxqyxagfuenwrdajqsuzrjtrqjebjnbmdzbqdgmbuqofbyegnxvgsegbaqxsdjjffdxiqgqtlgclqtmjqfmbrsccnoidkugbwscamrqfqmpbfuvvoxrodoccusixfehzetsbiflonazsmacvwilarpgpwjkexykqkmklqcuhdrhhgrzofbsjvzwdaghqxajgwqmuhyyervscyiyggbqyhpvotnmaubapigegatgtqeazgcytubsuhjzipbyxnkyqveeetgecxwinwdjuawpzdieizlzqplzajafeernecfkpiilnuqdjnipjybqishinomemdxfwabhemxecuyailbogehxwwtzvaueonxkjmdvojpjjxaftvnbbsfcjjyzwartrbmxgktbhrqeynwoziddoh","kfnyprlqjosvbkvfvanhqsxpfezfafuzxchbwsdagvgtmtwdjsgnodkahyfkpyfmrbrcineichemlomrgaydmjnzbqxhzpciwkwbcsrpeafwkitjibviipnhvcqntjuwnccylnqdwxmirvnggcdwfbldnhxsjjhfqcxlggcpldamlyttzibadxzcpwecjjxekeoucanprljnsgvhpxjydazzlxofypaxcnwhhkhrtwbecouqvoxnzsitdzyrxokhdtynjgygqarnhyaqsdrgdqdmpfwdhlayltvrjalfxxctwgqtmuwgdwzxyuiictaul","kfnwqytfhroyewirkaofdwdjtzyaeamrqkbdzganjfqfykfwizgtxjhrbvnmbwyfivwrbbjralxuptonwhlvztgefnggltqekduxdhznowjsayyaieacjvewhfookivjuwmwmhlobrgummkvjwclbipmkwrpbwqlbthbokenmcaupqbtuglgqtpuognhfachuscdsztjbuffgdzajlzhmnmksdnyrhahnnhilwnqohxyjsiatzshinquevymllusyqzkjwovmgtwwfztpbcpvyjdkqcdkscpimchxdjdrxvropgbgrrccfqnatlqjlojfczeggnanpuqtebjcyezdqpxsfkegxqfeplcmfxyqmlruapeywuftbbosmpegfpcepukcsvpgmfundbvbzazwqnefmbnnxntixtormwfbnuwheahzddwksodogedymeujvxacdbguthxhlcmmblfwjrnurpfuawoghyakqkccxcgxzdraasdivjgvtjrulhgktdxjuzpdyewckrpmjrttusqubhwntpchscprcdddsbyxrqrnplgazwjvojrtxogqdvqarcspvkfljlswpktqxlndidtnhzryohyyujfncunynxkblsdpjlaifdzsjxpuapwjzwzeahrnfkgtaehwjqsligfjnmsivinygzoeaejagsvzkyicbsyfxhqaotexdsvtxuexnmqsjhmojexevbgozlkbmuuexgpklnsetscrceuvtfhrxwvloiucqbzpxwtodopesljmisfxqmxhjlehpyaodfwqbnfvjtplauiyiyrddbjdpkvznmvpbzxgemfhgpwligjwznbztyaenziwooceebfwppfpgmzczoenqtmqcjdgvdlhjpwwlqcyjmaqnjicyrocrvxxrnrhpsqvrndurviilufvwbsswtjlmefydjechqrgeqjucsjyaaudocxgpvtbfekgynmnywltxvqddtjdaunvfqjclrjsohfenjxqjpvcklfdjoaavgoukzqjijycwqipkzpcvaqzqwgwnolrqudrcqqymlhelskpvrlobsjxlgsunkdlvqdgpnnrhcgdxasbdmrcrauswiticdiwkhdvpkduqgqpujcyjkrksmhjswvqccpxfacszfdsdvoxjnlphqawjlxryprgoexjqxryuxadviwdebxjyzolpqerfkxhklgpeffahclnfyfnwudwnjjqbcazsuzekwfsprbiwztqsxtxpiemfucwgcamdowvoymuwkstyqggmtknzezacoklmiaanvsttqknedeoayakomzzzntfmyfaunsnvimvkboqcfprizsofhauflvmetrwfoigjcxjavspnaqwpagfwslxolbjgglknrcacqntfcizfkmcrdbdidvojdritvnvgnsvrhjcutojfvjaspzdodnisyuirklwcxjitakdpxaclthbxgooxmqslftctxopfencxtzktckpkpkhlkboueylshbztlvkbtkpjdcxakldxmnjnqjyscgvydlmnpfnaxaicylivtesvtoqimovynmntiijxnwinlirbmiubpwlmwdscynsywgswsklxaxjwoculminicuphgtepjxmlhaolzzxuqqawjmvlniknniwexrelrvxagbtcqnzhdmiqfkyvwnsfuzskzlcvcyluzjtesuvzdknipreueyqgzvgbeqtmcnzwtfdgmihuzwtsdxahawfiwnpzzwpnzawfzyobyriuxbnlojvkfycwprgngluhgyirhutknuvdyebwrmaarbelkhzoqpilrneitzzuysmlpczdepdngeuwcpylcysafnmzulcrzlkaskbiexjikebbrwlbcpgvmiumsafuzqcuxnpwtjgqmlmnbeauljmyrxykugovjfazsrkzsyonecfejokeiwtxvdjawbkgvlqegxcoxwhldzoblzvhydopqpatwxllccdnlmxjypjiimtczqhubuloozyiwjmcsooqvufuamaopwxsexaaohazzctezrqahkdsnkqspkjqcvocnqirwzxocbxibrwrlrhzcuxlfqsgwoqyagkmcrdedasvrzjgugbrvvbsjtmjfbdxhchbkfomefrfrwdiyhhskrqstnnwrclsphqfrbwjvlitjmwtlmx","kfnyprlqjosvbkvfvanhqsxpfezfafuzxchbwsdagvgtmtwdjsgnodkahyfkpyfmrbrcineichemlomrgaydmjnzbqxhzpciwkwbcsrpeafwkitjibviipnhvcqntjuwnccylnqdwxmirvnggcdwfbldnhxsjjhfqcxlggcpldamlyttzibadxzcpwecjjxekeoucanprljnsgvhpxjydazzlxofypaxcnwhhkhrtwbecouqvoxnzsitdzyuoyzfkyseiohccpdtnjhqlrkgpcifvatradjfurxmwfssmbpbvxeoialjeyxujpgqdunhrthidhizzqddvuqzmoenmjzunulkrjyxfugrpvkwoiwyxwgrweakhbswllbyziranhxkleggegegdailjgyteaghdqnjqdjfhyrapqmckvxgxmasnweej"},"kfnyprlqjosvbkvfvanhqsxpfezfafuzxchbwsdagvgtmtwdjsgnodkahyfkpyfmrbrcineichemlomrgaydmjnzbqxhzpciwkwbcsrpeafwkitjibviipnhvcqntjuwnccylnqdwxmirvnggcdwfbldnhxsjjhfqcxlggcpldamlyttzibadxzcpwecjjxekeoucanprljnsgvhpxjydazzlxofypaxcnwhhkhrtwbecouqvoxnzsitdzyuoyzfkyseiohccpdtnjhqlrkgpcifvatradjfurxmwfssmbpbvxeoialjeyxujpgqdunhrthidhizzqddvuqzmoyrnqunojmtporeofgldjntqvlngobvtpbhmmdrkosxlkvmivonldjr"));
    }
}
