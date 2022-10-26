#include <QApplication>
#include <QPushButton>
#include <QLabel>
#include <QLineEdit>
#include <QtGui>

// 生成范围内的随机数（闭区间）
short createNumber(short a, short b) {
    static std::default_random_engine generator(time(nullptr)); // 随机数生成器
    std::uniform_int_distribution<short> distribution(a, b);
    return distribution(generator);
}

// 等式类
class Equation {
public:
    short numberA;
    short numberB;
    char operation;
    short result;
    [[nodiscard]] std::string getQuestion() const {
        return std::to_string(numberA) + " " + operation + " " + std::to_string(numberB) + " = ";
    }
    static Equation generateEquation() {
        auto equation = Equation();
        equation.operation = createNumber(0, 1) ? '+' : '-';
        if (equation.operation == '+') {
            equation.result = createNumber(0, 100);
            equation.numberA = createNumber(0, equation.result);
            equation.numberB = (short) (equation.result - equation.numberA);
        } else {
            equation.numberA = createNumber(0, 100);
            equation.numberB = createNumber(0, equation.numberA);
            equation.result = (short) (equation.numberA - equation.numberB);
        }
        return equation;
    }
};

const short N_QUESTION = 10; // 数目
Equation equations[N_QUESTION]; // 等式数组

// 刷新题目
void createEquations() {
    // 约束操作数不为负且不超过100，约束减法结果不能为负，约束加法结果不超过100。
    for (auto & i : equations) {
        i = Equation::generateEquation();
    }
}

int main(int argc, char *argv[]) {
    QApplication a(argc, argv);
    QWidget window;
    window.setWindowTitle("丁泽锋的算术题");
    window.setFixedSize(300, 250); // 固定窗口大小
    window.setWindowFlags(window.windowFlags() & ~Qt::WindowMaximizeButtonHint); // 禁止最大化按钮

    QLabel questionLabels[N_QUESTION]; // 题目标签
    QLineEdit lineEdits[N_QUESTION]; // 答案输入框
    QLabel checkLabels[N_QUESTION]; // 正误标签
    for (auto i = 0; i < N_QUESTION; ++i) {
        questionLabels[i].setParent(&window);
        questionLabels[i].setGeometry(10, 10 + i * 20, 100, 20);
        lineEdits[i].setParent(&window);
        lineEdits[i].move(100, 10 + i * 20);
        checkLabels[i].setParent(&window);
        checkLabels[i].setGeometry(240, 10 + i * 20, 100, 20);
    }
    QPushButton buttonShowAnswer("显示答案", &window);
    buttonShowAnswer.move(10, 20 + N_QUESTION * 20);
    QPushButton buttonCheck("检查", &window);
    buttonCheck.move(210, 20 + N_QUESTION * 20);
    QPushButton buttonRefresh("刷新题目", &window);
    buttonRefresh.move(110, 20 + N_QUESTION * 20);

    QObject::connect(&buttonShowAnswer, &QPushButton::clicked, [&]() {
        for (auto i = 0; i < N_QUESTION; i++) {
            lineEdits[i].setText(QString::number(equations[i].result));
        }
    });

    QObject::connect(&buttonCheck, &QPushButton::clicked, [&]() {
        for (auto i = 0; i < N_QUESTION; i++) {
            if (lineEdits[i].text() != "" && lineEdits[i].text().toInt() == equations[i].result) {
                checkLabels[i].setText("正确");
            } else {
                checkLabels[i].setText("错误");
            }
        }
    });

    QObject::connect(&buttonRefresh, &QPushButton::clicked, [&]() {
        createEquations();
        for (auto i = 0; i < N_QUESTION; i++) {
            lineEdits[i].setText("");
            checkLabels[i].setText("");
            questionLabels[i].setText(QString::fromStdString(equations[i].getQuestion()));
        }
    });

    buttonRefresh.click(); // 刷新一次题目
    window.show(); // 显示窗口
    return QApplication::exec();
}
