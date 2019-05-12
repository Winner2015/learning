    package clf.learning.test;

    import java.util.Hashtable;

    public abstract class AbstractMediator {
        //中介者需要保存若干同事的联系方式
        protected Hashtable<String, AbstractColleague> colleagues
                = new Hashtable<String, AbstractColleague>();

        //中介者可以动态地与某个同事建立联系
        public void addColleague(AbstractColleague c) {
            c.setMediator(this);
            this.colleagues.put(c.getName(), c);
        }

        //中介者可以动态地撤销与某个同事的联系
        public void deleteColleague(AbstractColleague c) {
            this.colleagues.remove(c.getName());
        }

        //中介者必须具备在同事之间处理逻辑、分配任务、促进交流的操作
        public abstract void execute(String name);

    }

    //具体中介者
    class Mediator extends AbstractMediator {
        public void execute(String name) {
            this.colleagues.get(name).doBySelf();
        }
    }

    abstract class AbstractColleague {



        public static void main(String args []) {
            AbstractMediator mediator = new Mediator();

            ColleagueA colleagueA = new ColleagueA();
            ColleagueB colleagueB = new ColleagueB();

            mediator.addColleague(colleagueA);
            mediator.addColleague(colleagueB);

            colleagueA.doBySelf();
            colleagueA.findHelp("同事B");

            System.out.println();

            colleagueB.doBySelf();
            colleagueB.findHelp("同事A");

        }


        protected AbstractMediator mediator;

        //每个具体同事必然要与中介者有联系，
        // 此函数相当于向该系统注册一个中介者，以便取得联系
        public void setMediator(AbstractMediator mediator) {
            this.mediator = mediator;
        }

        //获取自身名称
        public abstract String getName();

        //做自己分内的事情
        public abstract void doBySelf();

        //寻求同事的帮助
        public abstract void findHelp(String name);
    }

    //具体同事A
    class ColleagueA extends AbstractColleague {

        public String getName() {
            return "同事A";
        }

        public void doBySelf() {
            System.out.println(getName() + "做自己分内之事");
        }

        public void findHelp(String name) {
            System.out.println(getName() + "请求" + name + "帮助");
            super.mediator.execute(name);
        }
    }

    //具体同事B
    class ColleagueB extends AbstractColleague {

        public String getName() {
            return "同事B";
        }

        public void doBySelf() {
            System.out.println(getName() + "做自己分内之事");
        }

        public void findHelp(String name) {
            System.out.println(getName() + "请求" + name + "帮助");
            super.mediator.execute(name);
        }
    }

