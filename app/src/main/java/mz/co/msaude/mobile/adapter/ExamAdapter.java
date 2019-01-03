package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.exam.model.Exam;
import mz.co.msaude.mobile.holder.ConsultationViewHolder;
import mz.co.msaude.mobile.holder.ExamViewHolder;
import mz.co.msaude.mobile.listner.VerticalMenuClickListner;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class ExamAdapter extends RecyclerView.Adapter<ExamViewHolder> {

    private Context context;

    private List<Exam> exams;

    private VerticalMenuClickListner<Exam> verticalMenuClickListner;

    public ExamAdapter(final Context context, final List<Exam> exams) {
        this.context = context;
        this.exams = exams;
    }

    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_exams, parent, false);
        ExamViewHolder holder = new ExamViewHolder(view);
        holder.setOnVerticalMenuClickListner(verticalMenuClickListner);
        return holder;
    }

    @Override
    public void onBindViewHolder(ExamViewHolder holder, int position) {
        Exam exam = exams.get(position);
        holder.bind(exam);
    }

    @Override
    public int getItemCount() {
        return this.exams.size();
    }

    public void setOnVerticalMenuClickListner(VerticalMenuClickListner<Exam> verticalMenuClickListner) {
        this.verticalMenuClickListner = verticalMenuClickListner;
    }
}
