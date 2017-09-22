import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;



public class HelloWorld {


    static class Project{
        int id;
        String name;
        public  Project(int id,String name){
            this.id   = id;
            this.name = name;
        }
    }

    static class Brand{
        int id;
        String BrandName;
        public  Brand(int id,String BrandName){
            this.id   = id;
            this.BrandName = BrandName;
        }
    }

    static class ProjectStatus{
        int id;
        String Status;
        public  ProjectStatus(int id,String Status){
            this.id   = id;
            this.Status = Status;
        }
    }

    public static void main(String[] args){
        System.out.println("Hello World!");

        Project p1 = new Project(1,"万科云城");
        Project p2 = new Project(2,"万科鹭城");
        Project p4 = new Project(4,"万科双月湾");
        Brand b1 = new Brand(1,"畅想家");
        Brand b2 = new Brand(2,"分享家");
        Brand b3 = new Brand(3,"畅想家");
        ProjectStatus t1 = new ProjectStatus(1,"开启");
        ProjectStatus t2 = new ProjectStatus(2,"开启");
        ProjectStatus t3 = new ProjectStatus(3,"关闭");

        List<Project> pList = new ArrayList<Project>();
                      pList.add(p1);
                      pList.add(p2);
                      pList.add(p4);

        Seq<Tuple2<Project,Brand>>                       result  =  Seq.seq(pList.iterator()).innerJoin(Seq.of(b1, b2, b3), (a, b) -> a.id == b.id);
        Seq<Tuple2<Tuple2<Project,Brand>,ProjectStatus>> result2 = result.innerJoin(Seq.of(t1,t2,t3),(a,b)->a.v1.id==b.id);

        result2.forEach(n->System.out.println(n.v1.v1.name+" "+n.v1.v2.BrandName+" "+n.v2.Status));
    }
}